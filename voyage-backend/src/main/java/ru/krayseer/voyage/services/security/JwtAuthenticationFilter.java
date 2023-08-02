package ru.krayseer.voyage.services.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.ErrorResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String START_TOKEN_TAG = "Bearer ";

    private static final Integer INDEX_START_TOKEN_VALUE = 7;

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith(START_TOKEN_TAG)) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = authHeader.substring(INDEX_START_TOKEN_VALUE);
        String username;
        try {
            username = jwtService.extractUsername(jwt);
        } catch (Exception ex) {
            handleAuthenticationError(response);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    @SneakyThrows
    private void handleAuthenticationError(HttpServletResponse response) {
        log.error("Ошибка авторизации");
        ErrorResponse errorResponse = new ErrorResponse("Необходимо авторизоваться", ErrorCode.AUTHENTICATION_ERROR);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }

}
