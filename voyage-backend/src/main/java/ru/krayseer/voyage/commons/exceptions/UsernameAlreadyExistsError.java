package ru.krayseer.voyage.commons.exceptions;

public class UsernameAlreadyExistsError extends Error {

    public UsernameAlreadyExistsError() {
        super("this username already exists");
    }

}
