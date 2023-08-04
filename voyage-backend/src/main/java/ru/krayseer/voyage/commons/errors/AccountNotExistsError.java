package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class AccountNotExistsError extends Error {

    public AccountNotExistsError() {
        super("Такого аккаунта не существует", ErrorCode.ACCOUNT_NOT_EXISTS);
    }

}
