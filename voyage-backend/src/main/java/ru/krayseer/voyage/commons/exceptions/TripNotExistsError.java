package ru.krayseer.voyage.commons.exceptions;

public class TripNotExistsError extends Error {

    public TripNotExistsError() {
        super("there is no such trip");
    }

}
