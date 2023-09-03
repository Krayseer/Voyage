package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class TripNotExistsError extends BaseError {

    public TripNotExistsError() {
        super("Такой поездки не существует", ErrorCode.TRIP_NOT_EXISTS);
    }

}
