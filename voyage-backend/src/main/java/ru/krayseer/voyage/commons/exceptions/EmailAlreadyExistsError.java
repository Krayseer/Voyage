package ru.krayseer.voyage.commons.exceptions;

public class EmailAlreadyExistsError extends Error {

    public EmailAlreadyExistsError() {
        super("this email already exists");
    }

}
