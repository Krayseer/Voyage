package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class CarNotExistsError extends Error {

    public CarNotExistsError() {
        super("Такой машины не существует", ErrorCode.CAR_NOT_EXISTS);
    }

}
