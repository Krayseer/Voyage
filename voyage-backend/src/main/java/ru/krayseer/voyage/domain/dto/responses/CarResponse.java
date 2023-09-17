package ru.krayseer.voyage.domain.dto.responses;

import lombok.*;
import ru.krayseer.voyageapi.domain.dto.Response;

@Data
@Builder
public class CarResponse implements Response {

    private Long id;

    private String accountUsername;

    private String mark;

    private String color;

    private String licensePlate;

}
