package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class AccountNotExistsError extends Error {

    public AccountNotExistsError() {
        super("Такого аккаунта не существует", ErrorCode.ACCOUNT_NOT_EXISTS);
    }

}
