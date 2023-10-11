package ru.krayseer.voyage.commons.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.krayseer.voyage.errors.TripNotExistsError;
import ru.krayseer.voyage.domain.requests.FollowerRequest;
import ru.krayseer.voyage.domain.responses.AccountResponse;
import ru.krayseer.voyage.domain.responses.FollowerResponse;
import ru.krayseer.voyage.domain.Follower;
import ru.krayseer.voyage.domain.Trip;
import ru.krayseer.voyage.repositories.TripRepository;
import ru.krayseer.voyage.services.RemoteAccountService;
import ru.krayseer.voyageapi.domain.Request;
import ru.krayseer.voyageapi.commons.constants.mapper.Mapper;

@Component
@RequiredArgsConstructor
public class FollowerMapper implements Mapper<Follower> {

    private final TripRepository tripRepository;

    private final RemoteAccountService remoteAccountService;

    @Override
    public FollowerResponse createResponse(Follower follower) {
        AccountResponse followerAccount = remoteAccountService.getAccountInfo(follower.getAccountUsername());
        return FollowerResponse.builder()
                .username(followerAccount.getUsername())
                .name(followerAccount.getName())
                .phoneNumber(followerAccount.getPhoneNumber())
                .build();
    }

    @Override
    public Follower createEntity(Request request) {
        FollowerRequest followerRequest = (FollowerRequest) request;
        Trip trip = tripRepository.findById(followerRequest.getTripId()).orElseThrow(TripNotExistsError::new);
        return Follower.builder()
                .trip(trip)
                .accountUsername(followerRequest.getFollowerUsername())
                .build();
    }

}
