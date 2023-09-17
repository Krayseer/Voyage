package ru.krayseer.voyage.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.voyageapi.domain.dto.Response;
import ru.krayseer.voyageapi.errors.VoyageErrorCode;

@Data
@Builder
public class ErrorResponse implements Response {

    private String message;

    private VoyageErrorCode errorCode;

}
