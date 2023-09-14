package ru.krayseer.accountservice.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.accountservice.commons.constants.ErrorCode;
import ru.krayseer.accountservice.domain.dto.Response;

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
    private ErrorCode errorCode;

}
