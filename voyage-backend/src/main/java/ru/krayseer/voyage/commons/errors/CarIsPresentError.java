package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public class CarIsPresentError extends BaseError {

    public CarIsPresentError() {
        super("Машина уже используется в поездке", ErrorCode.CAR_IS_PRESENT);
    }

}
