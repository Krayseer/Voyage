package ru.krayseer.voyage.commons.errors;

import lombok.Getter;
import lombok.Setter;
import ru.krayseer.voyage.commons.constants.ErrorCode;

@Getter
@Setter
public abstract class Error extends RuntimeException {

    private ErrorCode errorCode;
    
    public Error(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
