package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class SubscribeError extends BaseError {

    public SubscribeError() {
        super("Вы не можете подписаться на свою поездку", ErrorCode.SELF_SUBSCRIBE);
    }

}
