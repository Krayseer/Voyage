package ru.krayseer.voyage.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public final class SecurityUtils {

    /**
     * @return авторизован ли пользователь
     */
    public static boolean isAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    /**
     * @return username авторизованного пользователя
     */
    public static String getUsername() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    /**
     * Авторизовать пользователя
     * @param userDetails данные о пользователе, которого нужно авторизовать
     * @param request тело запроса, нужно для создания деталей аутентификации (данные о браузере, IP-адресе...)
     */
    public static void authenticate(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
