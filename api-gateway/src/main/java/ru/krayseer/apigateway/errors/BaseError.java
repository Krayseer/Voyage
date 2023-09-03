package ru.krayseer.apigateway.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

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

    public BaseError(HttpClientErrorException exception) {
        super(exception.getMessage());
        var error = exception.getResponseBodyAs(ErrorResponse.class);
        Optional.ofNullable(error).ifPresent(err -> {
            this.message = err.getMessage();
            this.errorCode = err.getErrorCode();
        });
    }

}
