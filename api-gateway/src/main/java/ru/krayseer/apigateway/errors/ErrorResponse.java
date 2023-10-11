package ru.krayseer.apigateway.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krayseer.voyageapi.domain.Response;
import ru.krayseer.voyageapi.errors.VoyageErrorCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Response {

    private String message;

    private VoyageErrorCode errorCode;

}
