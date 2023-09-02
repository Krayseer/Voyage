package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.enums.ErrorCode;

public class PhoneNumberAlreadyExistsError extends Error {

    public PhoneNumberAlreadyExistsError() {
        super("Данный телефонный номер уже существует", ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
    }

}
