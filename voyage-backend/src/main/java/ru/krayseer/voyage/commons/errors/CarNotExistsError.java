package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class CarNotExistsError extends VoyageError {

    public CarNotExistsError() {
        super("Такой машины не существует", ErrorCode.CAR_NOT_EXISTS);
    }

}
