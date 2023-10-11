package ru.krayseer.voyage.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.krayseer.voyage.domain.responses.ErrorResponse;
import ru.krayseer.voyage.services.RemoteAccountService;
import ru.krayseer.voyage.utils.SecurityUtils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static ru.krayseer.voyage.commons.constants.ErrorCode.AUTHENTICATION_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final RemoteAccountService remoteAccountService;

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader == null) {
           filterChain.doFilter(request, response);
           return;
        }

        String username = remoteAccountService.getAccountUsername(authHeader);
        if (isUserNeedAuthenticate(username)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            SecurityUtils.authenticate(userDetails, request);
        } else {
            handleAuthenticationError(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isUserNeedAuthenticate(String username) {
        return username != null && !SecurityUtils.isAuthentication();
    }

    @SneakyThrows
    private void handleAuthenticationError(HttpServletResponse response) {
        log.error("Ошибка авторизации");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Необходимо авторизоваться")
                .errorCode(AUTHENTICATION_ERROR)
                .build();
        response.setStatus(UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
