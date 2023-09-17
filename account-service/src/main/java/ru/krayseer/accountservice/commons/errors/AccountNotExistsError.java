package ru.krayseer.accountservice.commons.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class AccountNotExistsError extends VoyageError {

    public AccountNotExistsError() {
        super("this account does not exist", ErrorCode.ACCOUNT_NOT_EXISTS);
    }

}
