package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class UsernameNotFoundError extends Error {

    public UsernameNotFoundError() {
        super("Такого username не существует", ErrorCode.USERNAME_NOT_EXISTS);
    }

}
