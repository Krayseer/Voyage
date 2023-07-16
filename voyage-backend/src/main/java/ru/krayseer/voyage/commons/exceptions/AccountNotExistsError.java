package ru.krayseer.voyage.commons.exceptions;

public class AccountNotExistsError extends Error {

    public AccountNotExistsError() {
        super("there is no such account");
    }

}
