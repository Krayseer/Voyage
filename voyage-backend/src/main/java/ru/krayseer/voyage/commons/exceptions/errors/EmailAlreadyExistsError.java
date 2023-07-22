package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class EmailAlreadyExistsError extends Error {

    public EmailAlreadyExistsError() {
        super("Данный адрес электронной почты уже существует", ErrorCode.EMAIL_ALREADY_EXISTS);
    }

}
