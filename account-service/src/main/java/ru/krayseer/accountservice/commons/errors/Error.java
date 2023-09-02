package ru.krayseer.accountservice.commons.errors;

import lombok.Getter;
import lombok.Setter;
import ru.krayseer.accountservice.commons.enums.ErrorCode;

@Getter
@Setter
public abstract class Error extends RuntimeException {

    private ErrorCode errorCode;
    
    public Error(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
