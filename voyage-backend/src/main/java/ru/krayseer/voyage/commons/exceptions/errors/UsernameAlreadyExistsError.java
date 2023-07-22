package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class UsernameAlreadyExistsError extends Error {

    public UsernameAlreadyExistsError() {
        super("Данный username уже существует", ErrorCode.USERNAME_ALREADY_EXISTS);
    }

}
