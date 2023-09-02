package ru.krayseer.voyage.commons.constants;

/**
 * Коды ошибок для response
 */
public enum ErrorCode {

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

}
