package ru.krayseer.voyage.domain.dto.requests;

import lombok.*;

import ru.krayseer.voyage.domain.dto.Request;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowerRequest implements Request {

    private Long tripId;

    private String followerUsername;

}
