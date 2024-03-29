package ru.krayseer.voyage.services.impl;

import lombok.extern.slf4j.Slf4j;
import ru.krayseer.voyage.commons.errors.SubscribeError;
import ru.krayseer.voyage.commons.errors.TripNotExistsError;
import ru.krayseer.voyageapi.domain.dto.Response;
import ru.krayseer.voyage.domain.dto.requests.FollowerRequest;
import ru.krayseer.voyage.domain.dto.requests.TripRequest;
import ru.krayseer.voyage.domain.dto.responses.TripResponse;
import ru.krayseer.voyage.domain.entities.Follower;
import ru.krayseer.voyage.domain.entities.Trip;
import ru.krayseer.voyage.domain.mappers.FollowerMapper;
import ru.krayseer.voyage.domain.mappers.TripMapper;
import ru.krayseer.voyage.domain.repositories.FollowerRepository;
import ru.krayseer.voyage.domain.repositories.TripRepository;
import ru.krayseer.voyage.services.TripService;
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

    private final TripMapper tripMapper;

    private final FollowerMapper followerMapper;

    @Override
    public List<TripResponse> loadAllTrips() {
        log.info("Load all trips");
        return tripRepository.findAll().stream().map(tripMapper::createResponse).toList();
    }

    @Override
    public Response createNewTrip(String username, TripRequest tripRequest) {
        tripRequest.setDriverUsername(username);
        Trip trip = tripMapper.createEntity(tripRequest);
        tripRepository.save(trip);
        log.info("Save new trip with id: {}", trip.getId());
        return tripMapper.createResponse(trip);
    }

    @Override
    public Response subscribeFollowerOnTrip(Long tripId, String username) {
        var trip = tripRepository.findById(tripId).orElseThrow(TripNotExistsError::new);
        if(trip.getDriverUsername().equals(username)) {
            throw new SubscribeError();
        }
        Follower follower = followerMapper.createEntity(new FollowerRequest(tripId, username));
        followersRepository.save(follower);
        log.info("Add follower with id {} on trip with id {}", tripId, follower.getId());
        return followerMapper.createResponse(follower);
    }

    @Override
    public Response updateTrip(Long tripId, TripRequest tripRequest) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(TripNotExistsError::new);
        trip.setPrice(tripRequest.getPrice());
        trip.setAddressFrom(tripRequest.getAddressFrom());
        trip.setAddressTo(tripRequest.getAddressTo());
        trip.setTimeTrip(tripRequest.getTimeTrip());
        trip.setCountSeats(tripRequest.getCountSeats());
        tripRepository.save(trip);
        log.info("Update trip with id: {}", tripId);
        return tripMapper.createResponse(trip);
    }

    @Override
    public void deleteTrip(Long tripId) {
        log.info("Delete trip with id: {}", tripId);
        tripRepository.deleteById(tripId);
    }

}
