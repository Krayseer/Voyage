package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;

public class PhoneNumberAlreadyExistsError extends BaseError {

    public PhoneNumberAlreadyExistsError() {
        super("this phone number already exists", ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
    }

}
