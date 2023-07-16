package ru.krayseer.voyage.domain.dto.requests;

import lombok.*;
import ru.krayseer.voyage.commons.validations.interfaces.LicensePlate;

import jakarta.validation.constraints.NotBlank;
import ru.krayseer.voyage.domain.dto.Request;

@Data
@Builder
public class CarRequest implements Request {

    @NotBlank(message = "нужно ввести марку машины")
    private String mark;

    @NotBlank(message = "нужно ввести цвет машины")
    private String color;

    @LicensePlate
    private String licensePlate;

    private String accountUsername;

}
