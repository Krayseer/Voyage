package ru.krayseer.voyage.commons.exceptions;

public class CarNotExistsError extends Error {

    public CarNotExistsError() {
        super("there is no such car");
    }

}
