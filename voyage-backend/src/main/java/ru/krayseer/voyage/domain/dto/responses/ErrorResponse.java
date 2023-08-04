package ru.krayseer.voyage.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.voyage.commons.constants.ErrorCode;
import ru.krayseer.voyage.domain.dto.Response;

@Data
@Builder
public class ErrorResponse implements Response{

    private String message;

    private ErrorCode errorCode;

}
