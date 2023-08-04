package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class EmailAlreadyExistsError extends Error {

    public EmailAlreadyExistsError() {
        super("Данный адрес электронной почты уже существует", ErrorCode.EMAIL_ALREADY_EXISTS);
    }

}
