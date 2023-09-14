package ru.krayseer.voyage.domain.dto.requests;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.krayseer.voyage.domain.dto.Request;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest implements Request {

    @NotNull(message = "нужно ввести цену поездки")
    private Integer price;

    @NotBlank(message = "нужно ввести адрес откуда едет водитель")
    private String addressFrom;

    @NotBlank(message = "нужно ввести адрес куда едет водитель")
    private String addressTo;

    @NotNull(message = "нужно ввести допустимое колличество пассажиров")
    private Integer countSeats;

    @NotNull(message = "нужно указать дату и время поездки")
    private LocalDateTime timeTrip;

    @NotNull(message = "нужно указать id машины на которой будет поездка")
    private Long carId;

    private String driverUsername;

}
