package ru.krayseer.voyage.domain.responses;

import lombok.*;
import ru.krayseer.voyageapi.domain.Response;

@Data
@Builder
public class FollowerResponse implements Response {

    private String username;

    private String name;

    private String phoneNumber;

}
