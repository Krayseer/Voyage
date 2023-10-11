package ru.krayseer.accountservice.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;

import java.util.Objects;

import static ru.krayseer.accountservice.commons.constants.SecurityConstants.INDEX_START_TOKEN_VALUE;

public final class Utils {

    /**
     * Получить jwt токен из заголовка авторизации
     * @param request запрос с заголовком авторизации
     * @return jwt токен
     */
    public static String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !isNullOrEmptyString(header) ? header.substring(INDEX_START_TOKEN_VALUE) : null;
    }

    /**
     * Проверить пустая ли строка
     * @param str строка, которую нужно проверить
     */
    public static Boolean isNullOrEmptyString(String str) {
        return str == null || str.trim().length() == 0;
    }
}
