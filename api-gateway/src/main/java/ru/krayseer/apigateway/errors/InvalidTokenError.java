package ru.krayseer.apigateway.errors;

import org.springframework.web.client.HttpClientErrorException;
import ru.krayseer.voyageapi.errors.VoyageError;

import java.util.Optional;

public class InvalidTokenError extends VoyageError {

    public InvalidTokenError(HttpClientErrorException exception) {
        super();
        var error = exception.getResponseBodyAs(ErrorResponse.class);
        Optional.ofNullable(error).ifPresent(err -> {
            this.message = err.getMessage();
            this.errorCode = err.getErrorCode();
        });
    }

}
