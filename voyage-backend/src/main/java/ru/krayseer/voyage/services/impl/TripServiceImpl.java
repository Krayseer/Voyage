package ru.krayseer.voyage.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import ru.krayseer.voyage.commons.exceptions.errors.SubscribeError;
import ru.krayseer.voyage.commons.exceptions.errors.TripNotExistsError;
import ru.krayseer.voyage.domain.dto.requests.FollowerRequest;
import ru.krayseer.voyage.domain.dto.requests.TripRequest;
import ru.krayseer.voyage.domain.dto.responses.FollowerResponse;
import ru.krayseer.voyage.domain.dto.responses.TripResponse;
import ru.krayseer.voyage.domain.entities.Follower;
import ru.krayseer.voyage.domain.entities.Trip;
import ru.krayseer.voyage.domain.repositories.FollowerRepository;
import ru.krayseer.voyage.domain.repositories.TripRepository;
import ru.krayseer.voyage.commons.events.DeleteTripEvent;
import ru.krayseer.voyage.services.TripService;
import ru.krayseer.voyage.services.factories.FollowerFactory;
import ru.krayseer.voyage.services.factories.TripFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    private final FollowerRepository followersRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final TripFactory tripFactory;

    private final FollowerFactory followerFactory;

    @Override
    @Cacheable("trips")
    public List<TripResponse> loadAllTrips() {
        log.info("Load all trips");
        return tripRepository.findAll().stream().map(tripFactory::createResponse).toList();
    }

    @Override
    @CacheEvict(value="trips", allEntries=true)
    public TripResponse createNewTrip(String username, TripRequest tripRequest) {
        tripRequest.setDriverUsername(username);
        Trip trip = tripFactory.createObjectFrom(tripRequest);
        tripRepository.save(trip);
        log.info("Save new trip with id: {}", trip.getId());
        return tripFactory.createResponse(trip);
    }

    @Override
    @CacheEvict(value="trips", allEntries=true)
    public FollowerResponse subscribeFollowerOnTrip(Long tripId, String username) {
        var trip = tripRepository.findById(tripId).orElseThrow(TripNotExistsError::new);
        if(trip.getDriver().getUsername().equals(username)) {
            throw new SubscribeError();
        }
        Follower follower = followerFactory.createObjectFrom(
                FollowerRequest
                        .builder()
                        .tripId(tripId)
                        .followerUsername(username)
                        .build());
        followersRepository.save(follower);
        log.info("Add follower with id {} on trip with id {}", tripId, follower.getId());
        return followerFactory.createResponse(follower);
    }

    @Override
    @CacheEvict(value="trips", allEntries=true)
    public TripResponse updateTrip(Long tripId, TripRequest tripRequest) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(TripNotExistsError::new);
        trip.setPrice(tripRequest.getPrice());
        trip.setAddressFrom(tripRequest.getAddressFrom());
        trip.setAddressTo(tripRequest.getAddressTo());
        trip.setTimeTrip(tripRequest.getTimeTrip());
        trip.setCountSeats(tripRequest.getCountSeats());
        tripRepository.save(trip);
        log.info("Update trip with id: {}", tripId);
        return tripFactory.createResponse(trip);
    }

    @Override
    @CacheEvict(value="trips", allEntries=true)
    public void deleteTrip(Long tripId) {
        var trip = tripRepository.findById(tripId).orElseThrow(TripNotExistsError::new);
        log.info("Delete trip with id: {}", tripId);
        eventPublisher.publishEvent(new DeleteTripEvent(this, trip));
        tripRepository.deleteById(tripId);
    }

}
