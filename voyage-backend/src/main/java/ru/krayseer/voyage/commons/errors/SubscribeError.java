package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class SubscribeError extends VoyageError {

    public SubscribeError() {
        super("Вы не можете подписаться на свою поездку", ErrorCode.SELF_SUBSCRIBE);
    }

}
