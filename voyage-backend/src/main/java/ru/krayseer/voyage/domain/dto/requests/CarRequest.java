package ru.krayseer.voyage.domain.dto.requests;

import lombok.*;
import ru.krayseer.voyage.commons.validations.LicensePlate;

import jakarta.validation.constraints.NotBlank;
import ru.krayseer.voyageapi.domain.dto.Request;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest implements Request {

    @NotBlank(message = "нужно ввести марку машины")
    private String mark;

    @NotBlank(message = "нужно ввести цвет машины")
    private String color;

    @LicensePlate
    private String licensePlate;

    private String accountUsername;

}
