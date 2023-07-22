package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class TripNotExistsError extends Error {

    public TripNotExistsError() {
        super("Такой поездки не существует", ErrorCode.TRIP_NOT_EXISTS);
    }

}
