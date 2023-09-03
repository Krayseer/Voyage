package ru.krayseer.voyage.commons.errors;

import lombok.Getter;
import lombok.Setter;
import ru.krayseer.voyage.commons.constants.ErrorCode;

@Getter
@Setter
public class BaseError extends RuntimeException {

    private String message;

    private ErrorCode errorCode;
    
    public BaseError(String message, ErrorCode errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

}
