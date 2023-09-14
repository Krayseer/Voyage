package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;

public class UsernameNotFoundError extends BaseError {

    public UsernameNotFoundError() {
        super("this username does not exist", ErrorCode.USERNAME_NOT_EXISTS);
    }

}
