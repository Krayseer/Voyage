package ru.krayseer.voyage.commons.exceptions;

import lombok.Getter;
import lombok.Setter;
import ru.krayseer.voyage.commons.constants.ErrorCode;

@Getter
@Setter
public class Error extends RuntimeException {

    private ErrorCode errorCode;
    
    public Error(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
