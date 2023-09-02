package ru.krayseer.accountservice.commons.enums;

/**
 * Коды ошибок для response
 */
public enum ErrorCode {

    /**
     * Аккаунта не существует
     */
    ACCOUNT_NOT_EXISTS,

    /**
     * Электронная почта уже существует
     */
    EMAIL_ALREADY_EXISTS,

    /**
     * Телефонный номер уже существует
     */
    PHONE_NUMBER_ALREADY_EXISTS,

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
