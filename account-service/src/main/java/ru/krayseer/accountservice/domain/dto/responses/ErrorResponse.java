package ru.krayseer.accountservice.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.accountservice.commons.enums.ErrorCode;
import ru.krayseer.accountservice.domain.dto.Response;

@Data
@Builder
public class ErrorResponse implements Response {

    private String message;

    private ErrorCode errorCode;

}
