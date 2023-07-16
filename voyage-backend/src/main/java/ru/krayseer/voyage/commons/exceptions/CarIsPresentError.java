package ru.krayseer.voyage.commons.exceptions;

public class CarIsPresentError extends Error {

    public CarIsPresentError() {
        super("this car is present on the trip");
    }

}
