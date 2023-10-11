package ru.krayseer.accountservice.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class UsernameNotFoundError extends VoyageError {

    public UsernameNotFoundError() {
        super("this username does not exist", ErrorCode.USERNAME_NOT_EXISTS);
    }

}
