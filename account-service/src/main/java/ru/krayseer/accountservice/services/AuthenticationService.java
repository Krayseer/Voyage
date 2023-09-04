package ru.krayseer.accountservice.services;

import ru.krayseer.accountservice.domain.dto.Response;
import ru.krayseer.accountservice.domain.dto.requests.AuthRequest;
import ru.krayseer.accountservice.domain.dto.responses.AuthResponse;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;

public interface AuthenticationService {

    /**
     * Регистрация пользователя
     * @param request данные о пользователе
     * @return jwt токен авторизованного пользователя
     */
    Response registerUser(RegisterRequest request);

    /**
     * Регистрация администратора
     * @param request данные об администраторе
     * @param secret секретный ключ, без которого создать администратора будет невозможно
     * @return jwt токен авторизованного пользователя
     */
    Response registerAdmin(RegisterRequest request, Integer secret);

    /**
     * Аутентификация пользователя
     * @param request данные об аутенифицированном пользователе
     * @return jwt токен авторизованного пользователя
     */
    Response authenticate(AuthRequest request);

    boolean validateToken(String authHeader);

    Response getAccountAuthInfo(String token);

}
