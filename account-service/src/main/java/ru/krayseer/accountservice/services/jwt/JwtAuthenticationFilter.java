package ru.krayseer.accountservice.services.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.krayseer.accountservice.domain.dto.responses.ErrorResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static ru.krayseer.accountservice.commons.enums.ErrorCode.AUTHENTICATION_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String START_TOKEN_TAG = "Bearer ";

    private static final Integer INDEX_START_TOKEN_VALUE = 7;

    private final JwtService jwtService;

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {
        var authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(START_TOKEN_TAG)) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = authHeader.substring(INDEX_START_TOKEN_VALUE);
        try {
            jwtService.extractUsername(token);
        } catch (Exception ex) {
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
