package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class PhoneNumberAlreadyExistsError extends Error {

    public PhoneNumberAlreadyExistsError() {
        super("Данный телефонный номер уже существует", ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
    }

}
