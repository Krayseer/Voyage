package ru.krayseer.voyage.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {

    /**
     * Получить имя пользователя из токена
     * @param token токен
     * @return username пользователя
     */
    String extractUsername(String token);

    /**
     * Создать токен для пользователя
     */
    String generateToken(UserDetails userDetails);

    /**
     * Валидный ли токен
     */
    Boolean isTokenValid(String token, UserDetails userDetails);

}
