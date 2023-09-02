package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.enums.ErrorCode;

public class EmailAlreadyExistsError extends Error {

    public EmailAlreadyExistsError() {
        super("Данный адрес электронной почты уже существует", ErrorCode.EMAIL_ALREADY_EXISTS);
    }

}
