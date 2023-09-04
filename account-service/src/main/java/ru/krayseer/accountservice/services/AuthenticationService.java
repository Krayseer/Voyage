package ru.krayseer.accountservice.services;

import ru.krayseer.accountservice.domain.dto.Response;
import ru.krayseer.accountservice.domain.dto.requests.AuthRequest;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;

public interface AuthenticationService {

    /**
     * Регистрация пользователя
     * @param request данные о пользователе
     * @return данные об авторизации пользователя
     */
    Response registerUser(RegisterRequest request);

    /**
     * Регистрация администратора
     * @param request данные об администраторе
     * @param secret секретный ключ, без которого создать администратора будет невозможно
     * @return данные об авторизации пользователя
     */
    Response registerAdmin(RegisterRequest request, Integer secret);

    /**
     * Аутентификация пользователя
     * @param request данные об аутенифицированном пользователе
     * @return данные об авторизации пользователя
     */
    Response authenticate(AuthRequest request);

    /**
     * Получить username из jwt токена
     * @param header заголовок авторизации
     * @return username пользователя
     */
    String getUsernameFromToken(String header);

    /**
     * Проверить jwt токен на валидность
     * @param authHeader заголовок авторизации
     * @return true, если jwt токен валиден, иначе false
     */
    boolean validateToken(String authHeader);

    /**
     * Получить данные об авторизации пользователя
     * @param token jwt токен пользователя
     * @return данные об авторизации пользователя
     */
    Response getAccountAuthInfo(String token);

}
