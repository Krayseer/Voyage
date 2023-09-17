package ru.krayseer.accountservice.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.accountservice.commons.constants.ErrorCode;
import ru.krayseer.voyageapi.domain.dto.Response;
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
