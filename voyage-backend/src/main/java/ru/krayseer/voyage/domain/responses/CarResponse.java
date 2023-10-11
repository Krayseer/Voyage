package ru.krayseer.voyage.domain.responses;

import lombok.*;
import ru.krayseer.voyageapi.domain.Response;

@Data
@Builder
public class CarResponse implements Response {

    private Long id;

    private String accountUsername;

    private String mark;

    private String color;

    private String licensePlate;

}
