package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class TripNotExistsError extends Error {

    public TripNotExistsError() {
        super("Такой поездки не существует", ErrorCode.TRIP_NOT_EXISTS);
    }

}
