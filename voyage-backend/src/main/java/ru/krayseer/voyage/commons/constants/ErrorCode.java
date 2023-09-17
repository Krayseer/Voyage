package ru.krayseer.voyage.commons.constants;

import ru.krayseer.voyageapi.errors.VoyageErrorCode;

/**
 * Коды ошибок для response
 */
public enum ErrorCode implements VoyageErrorCode {

    /**
     * Машина используется в поездке
     */
    CAR_IS_PRESENT,

    /**
     * Машины не существует
     */
    CAR_NOT_EXISTS,

    /**
     * Ошибка подписки на свою поездку
     */
    SELF_SUBSCRIBE,

    /**
     * Поездки не существует
     */
    TRIP_NOT_EXISTS,

    /**
     * Ошибка авторизации
     */
    AUTHENTICATION_ERROR

}
