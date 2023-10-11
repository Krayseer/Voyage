package ru.krayseer.accountservice.errors;

import ru.krayseer.accountservice.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class InvalidAdminSecretKeyError extends VoyageError {

    public InvalidAdminSecretKeyError() {
        super("invalid secret admin key", ErrorCode.INVALID_ADMIN_SECRET_KEY);
    }

}
