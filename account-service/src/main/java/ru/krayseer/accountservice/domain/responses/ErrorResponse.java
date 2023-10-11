package ru.krayseer.accountservice.domain.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.voyageapi.domain.Response;
import ru.krayseer.voyageapi.errors.VoyageErrorCode;

@Data
@Builder
public class ErrorResponse implements Response {

    /**
     * Сообщение об ошибке
     */
    private String message;

    /**
     * Код ошибки
     */
    private VoyageErrorCode errorCode;

}
