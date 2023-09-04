package ru.krayseer.voyage.commons.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.krayseer.voyage.domain.dto.responses.ErrorResponse;
import ru.krayseer.voyage.services.RemoteAccountService;

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
        var username = remoteAccountService.getAccountUsername(request.getHeader(AUTHORIZATION));
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(username);
            var authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            handleAuthenticationError(response);
            return;
        }
        filterChain.doFilter(request, response);
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
