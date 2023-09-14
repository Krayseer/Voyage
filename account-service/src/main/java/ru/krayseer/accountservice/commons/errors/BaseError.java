package ru.krayseer.accountservice.commons.errors;

import lombok.Getter;
import lombok.Setter;
import ru.krayseer.accountservice.commons.constants.ErrorCode;

@Getter
@Setter
public abstract class BaseError extends RuntimeException {

    private ErrorCode errorCode;
    
    public BaseError(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
