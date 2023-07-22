package ru.krayseer.voyage.services.factories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.krayseer.voyage.commons.exceptions.errors.AccountNotExistsError;
import ru.krayseer.voyage.commons.exceptions.errors.TripNotExistsError;
import ru.krayseer.voyage.domain.dto.requests.FollowerRequest;
import ru.krayseer.voyage.domain.dto.responses.FollowerResponse;
import ru.krayseer.voyage.domain.entities.Account;
import ru.krayseer.voyage.domain.entities.Follower;
import ru.krayseer.voyage.domain.entities.Trip;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.domain.repositories.TripRepository;

@Component
@RequiredArgsConstructor
public class FollowerFactory extends BaseFactory<Follower, FollowerRequest> {

    private final TripRepository tripRepository;

    private final AccountRepository accountRepository;

    @Override
    public FollowerResponse createResponse(Follower follower) {
        return FollowerResponse
                .builder()
                .username(follower.getAccount().getUsername())
                .name(follower.getAccount().getName())
                .phoneNumber(follower.getAccount().getPhoneNumber())
                .build();
    }

    @Override
    public Follower createObjectFrom(FollowerRequest followerRequest) {
        Trip trip = tripRepository.findById(followerRequest.getTripId()).orElseThrow(TripNotExistsError::new);
        Account account = accountRepository.findByUsername(followerRequest.getFollowerUsername())
                .orElseThrow(AccountNotExistsError::new);
        return Follower
                .builder()
                .trip(trip)
                .account(account)
                .build();
    }

}
