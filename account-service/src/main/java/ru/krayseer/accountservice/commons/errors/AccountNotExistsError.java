package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;

public class AccountNotExistsError extends BaseError {

    public AccountNotExistsError() {
        super("this account does not exist", ErrorCode.ACCOUNT_NOT_EXISTS);
    }

}
