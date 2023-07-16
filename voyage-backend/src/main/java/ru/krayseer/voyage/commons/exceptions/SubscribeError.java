package ru.krayseer.voyage.commons.exceptions;

public class SubscribeError extends Error {

    public SubscribeError() {
        super("can't subscribe to your trip");
    }

}
