package ru.krayseer.voyage.commons.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.krayseer.voyage.domain.dto.responses.ErrorResponse;
import ru.krayseer.voyageapi.errors.VoyageError;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(VoyageError.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponse handlerException(VoyageError ex) {
        log.error("Ошибка: " + ex.getMessage());
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validationHandler(MethodArgumentNotValidException ex) {
        log.error("Ошибка валидации");
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return errors;
    }

}
