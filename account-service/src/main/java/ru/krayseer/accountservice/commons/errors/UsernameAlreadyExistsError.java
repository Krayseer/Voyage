package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;

public class UsernameAlreadyExistsError extends BaseError {

    public UsernameAlreadyExistsError() {
        super("this username already exists", ErrorCode.USERNAME_ALREADY_EXISTS);
    }

}
