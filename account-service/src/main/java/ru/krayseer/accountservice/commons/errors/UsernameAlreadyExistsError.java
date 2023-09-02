package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.enums.ErrorCode;

public class UsernameAlreadyExistsError extends Error {

    public UsernameAlreadyExistsError() {
        super("Данный username уже существует", ErrorCode.USERNAME_ALREADY_EXISTS);
    }

}
