package ru.krayseer.voyage.commons.constants;

/**
 * Коды ошибок для response
 */
public enum ErrorCode {

    /**
     * Аккаунта не существует
     */
    ACCOUNT_NOT_EXISTS,
    /**
     * Машина используется в поездке
     */
    CAR_IS_PRESENT,
    /**
     * Машины не существует
     */
    CAR_NOT_EXISTS,
    /**
     * Электронная почта уже существует
     */
    EMAIL_ALREADY_EXISTS,
    /**
     * Телефонный номер уже существует
     */
    PHONE_NUMBER_ALREADY_EXISTS,
    /**
     * Ошибка подписки на свою поездку
     */
    SELF_SUBSCRIBE,
    /**
     * Поездки не существует
     */
    TRIP_NOT_EXISTS,
    /**
     * Username уже существует
     */
    USERNAME_ALREADY_EXISTS,
    /**
     * Username не существует
     */
    USERNAME_NOT_EXISTS,
    /**
     * Ошибка авторизации
     */
    AUTHENTICATION_ERROR

}
