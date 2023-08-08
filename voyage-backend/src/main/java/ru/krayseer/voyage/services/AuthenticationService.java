package ru.krayseer.voyage.services;

import ru.krayseer.voyage.domain.dto.requests.AuthenticationRequest;
import ru.krayseer.voyage.domain.dto.requests.RegisterRequest;
import ru.krayseer.voyage.domain.dto.responses.AuthResponse;

public interface AuthenticationService {

    /**
     * Регистрация пользователя
     * @param request данные о пользователе
     * @return jwt токен авторизованного пользователя
     */
    AuthResponse registerUser(RegisterRequest request);

    /**
     * Регистрация администратора
     * @param request данные об администраторе
     * @param secret секретный ключ, без которого создать администратора будет невозможно
     * @return jwt токен авторизованного пользователя
     */
    AuthResponse registerAdmin(RegisterRequest request, Integer secret);

    /**
     * Аутентификация пользователя
     * @param request данные об аутенифицированном пользователе
     * @return jwt токен авторизованного пользователя
     */
    AuthResponse authenticate(AuthenticationRequest request);

}
