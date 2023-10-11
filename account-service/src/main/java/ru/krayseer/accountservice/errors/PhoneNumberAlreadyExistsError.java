package ru.krayseer.accountservice.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class PhoneNumberAlreadyExistsError extends VoyageError {

    public PhoneNumberAlreadyExistsError() {
        super("this phone number already exists", ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
    }

}
