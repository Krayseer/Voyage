package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class PhoneNumberAlreadyExistsError extends Error {

    public PhoneNumberAlreadyExistsError() {
        super("Данный телефонный номер уже существует", ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
    }

}
