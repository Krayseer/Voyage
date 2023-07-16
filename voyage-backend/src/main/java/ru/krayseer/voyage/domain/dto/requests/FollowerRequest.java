package ru.krayseer.voyage.domain.dto.requests;

import lombok.*;

import ru.krayseer.voyage.domain.dto.Request;

@Data
@Builder
public class FollowerRequest implements Request {

    private Long tripId;

    private String followerUsername;

}
