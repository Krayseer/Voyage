package ru.krayseer.accountservice.services;

public interface RedisService {

    /**
     * Получить jwt токен пользователя
     * @param username пользователь, чей токен нужно получить
     * @return jwt токен пользователя
     */
    String getUsernameToken(String username);

}
