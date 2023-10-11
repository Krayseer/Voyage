package ru.krayseer.voyage.domain.requests;

import lombok.*;

import ru.krayseer.voyageapi.domain.Request;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowerRequest implements Request {

    private Long tripId;

    private String followerUsername;

}
