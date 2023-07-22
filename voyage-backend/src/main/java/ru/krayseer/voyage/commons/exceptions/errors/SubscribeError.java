package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class SubscribeError extends Error {

    public SubscribeError() {
        super("Вы не можете подписаться на свою поездку", ErrorCode.SELF_SUBSCRIBE);
    }

}
