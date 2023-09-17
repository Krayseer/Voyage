package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class TripNotExistsError extends VoyageError {

    public TripNotExistsError() {
        super("Такой поездки не существует", ErrorCode.TRIP_NOT_EXISTS);
    }

}
