package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.enums.ErrorCode;

public class UsernameNotFoundError extends Error {

    public UsernameNotFoundError() {
        super("Такого username не существует", ErrorCode.USERNAME_NOT_EXISTS);
    }

}
