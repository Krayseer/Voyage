package ru.krayseer.apigateway.errors;

import ru.krayseer.voyageapi.errors.VoyageErrorCode;

/**
 * Коды ошибок для response
 */
public enum ErrorCode implements VoyageErrorCode {

    /**
     * Отсутствие заголовка авторизации
     */
    MISSING_AUTHORIZATION_HEADER,

    /**
     * Ошибка авторизации
     */
    AUTHENTICATION_ERROR,

}
