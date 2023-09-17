package ru.krayseer.voyage.commons.errors;

import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.errors.VoyageError;

public class CarIsPresentError extends VoyageError {

    public CarIsPresentError() {
        super("Машина уже используется в поездке", ErrorCode.CAR_IS_PRESENT);
    }

}
