package ru.krayseer.voyage.domain.dto.responses;

import lombok.*;
import ru.krayseer.voyage.domain.dto.Response;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TripResponse implements Response {

    private Long id;

    private String driverUsername;

    private Integer price;

    private String addressFrom;

    private String addressTo;

    private Integer countSeats;

    private List<FollowerResponse> followers;

    private LocalDateTime tripTime;

    private Long carId;

}
