package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class UsernameAlreadyExistsError extends Error {

    public UsernameAlreadyExistsError() {
        super("Данный username уже существует", ErrorCode.USERNAME_ALREADY_EXISTS);
    }

}
