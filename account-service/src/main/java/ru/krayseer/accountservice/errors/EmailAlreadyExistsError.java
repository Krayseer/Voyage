package ru.krayseer.accountservice.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class EmailAlreadyExistsError extends VoyageError {

    public EmailAlreadyExistsError() {
        super("this email address already exists", ErrorCode.EMAIL_ALREADY_EXISTS);
    }

}
