package ru.krayseer.voyage.commons.exceptions.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.commons.exceptions.Error;

public class CarIsPresentError extends Error {

    public CarIsPresentError() {
        super("Машина уже используется в поездке", ErrorCode.CAR_IS_PRESENT);
    }

}
