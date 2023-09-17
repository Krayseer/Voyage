package ru.krayseer.voyage.domain.dto.responses;

import lombok.*;
import ru.krayseer.voyageapi.domain.dto.Response;

@Data
@Builder
public class FollowerResponse implements Response {

    private String username;

    private String name;

    private String phoneNumber;

}
