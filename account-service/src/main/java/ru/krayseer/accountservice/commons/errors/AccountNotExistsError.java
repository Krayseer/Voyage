package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.enums.ErrorCode;

public class AccountNotExistsError extends Error {

    public AccountNotExistsError() {
        super("Такого аккаунта не существует", ErrorCode.ACCOUNT_NOT_EXISTS);
    }

}
