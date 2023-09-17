package ru.krayseer.voyage.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.krayseer.voyage.commons.errors.TripNotExistsError;
import ru.krayseer.voyage.domain.dto.requests.FollowerRequest;
import ru.krayseer.voyage.domain.dto.responses.FollowerResponse;
import ru.krayseer.voyage.domain.entities.Follower;
import ru.krayseer.voyage.domain.entities.Trip;
import ru.krayseer.voyage.domain.repositories.TripRepository;
import ru.krayseer.voyage.services.RemoteAccountService;
import ru.krayseer.voyageapi.domain.mapper.Mapper;

@Component
@RequiredArgsConstructor
public class FollowerMapper implements Mapper<Follower, FollowerRequest> {

    private final TripRepository tripRepository;

    private final RemoteAccountService remoteAccountService;

    @Override
    public FollowerResponse createResponse(Follower follower) {
        var followerAccount = remoteAccountService.getAccountInfo(follower.getAccountUsername());
        return FollowerResponse.builder()
                .username(followerAccount.getUsername())
                .name(followerAccount.getName())
                .phoneNumber(followerAccount.getPhoneNumber())
                .build();
    }

    @Override
    public Follower createEntity(FollowerRequest followerRequest) {
        Trip trip = tripRepository.findById(followerRequest.getTripId()).orElseThrow(TripNotExistsError::new);
        return Follower.builder()
                .trip(trip)
                .accountUsername(followerRequest.getFollowerUsername())
                .build();
    }

}
