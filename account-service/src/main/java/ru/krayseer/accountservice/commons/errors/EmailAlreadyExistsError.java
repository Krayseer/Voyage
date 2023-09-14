package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;

public class EmailAlreadyExistsError extends BaseError {

    public EmailAlreadyExistsError() {
        super("this email address already exists", ErrorCode.EMAIL_ALREADY_EXISTS);
    }

}
