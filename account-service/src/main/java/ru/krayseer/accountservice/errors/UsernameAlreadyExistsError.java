package ru.krayseer.accountservice.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class UsernameAlreadyExistsError extends VoyageError {

    public UsernameAlreadyExistsError() {
        super("this username already exists", ErrorCode.USERNAME_ALREADY_EXISTS);
    }

}
