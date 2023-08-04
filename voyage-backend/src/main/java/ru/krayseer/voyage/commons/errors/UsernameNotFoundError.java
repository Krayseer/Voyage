package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class UsernameNotFoundError extends Error {

    public UsernameNotFoundError() {
        super("Такого username не существует", ErrorCode.USERNAME_NOT_EXISTS);
    }

}
