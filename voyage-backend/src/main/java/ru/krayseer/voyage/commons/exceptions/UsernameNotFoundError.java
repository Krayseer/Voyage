package ru.krayseer.voyage.commons.exceptions;

public class UsernameNotFoundError extends Error {

    public UsernameNotFoundError() {
        super("username not found");
    }

}
