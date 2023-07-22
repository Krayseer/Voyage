package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class CarNotExistsError extends Error {

    public CarNotExistsError() {
        super("Такой машины не существует", ErrorCode.CAR_NOT_EXISTS);
    }

}
