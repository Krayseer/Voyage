package ru.krayseer.voyage.commons.exceptions;

public class PhoneNumberAlreadyExistsError extends Error {

    public PhoneNumberAlreadyExistsError() {
        super("this phone number already exists");
    }

}
