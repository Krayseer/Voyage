package ru.krayseer.voyage.domain.mappers;

import org.springframework.stereotype.Component;
import ru.krayseer.voyage.commons.errors.CarNotExistsError;
import ru.krayseer.voyage.domain.dto.requests.TripRequest;
import ru.krayseer.voyage.domain.dto.responses.FollowerResponse;
import ru.krayseer.voyage.domain.dto.responses.TripResponse;
import ru.krayseer.voyage.domain.entities.Trip;
import lombok.RequiredArgsConstructor;
import ru.krayseer.voyage.domain.repositories.CarRepository;
import ru.krayseer.voyage.services.RemoteAccountService;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TripMapper implements Mapper<Trip, TripRequest> {

    private final CarRepository carRepository;

    private final RemoteAccountService remoteAccountService;

    @Override
    public TripResponse createResponse(Trip trip) {
        List<FollowerResponse> tripFollowers = trip.getFollowers() == null ? Collections.emptyList() : trip.getFollowers()
                .stream()
                .map(follower ->  {
                    var followerAccount = remoteAccountService.getAccountInfo(follower.getAccountUsername());
                    return FollowerResponse.builder()
                            .username(followerAccount.getUsername())
                            .name(followerAccount.getName())
                            .phoneNumber(followerAccount.getPhoneNumber())
                            .build();
                })
                .toList();
        return TripResponse.builder()
                .id(trip.getId())
                .driverUsername(trip.getDriverUsername())
                .price(trip.getPrice())
                .addressFrom(trip.getAddressFrom())
                .addressTo(trip.getAddressTo())
                .countSeats(trip.getCountSeats())
                .followers(tripFollowers)
                .tripTime(trip.getTimeTrip())
                .carId(trip.getCar().getId())
                .build();
    }

    @Override
    public Trip createEntity(TripRequest tripRequest) {
        var car = carRepository.findById(tripRequest.getCarId()).orElseThrow(CarNotExistsError::new);
        return Trip.builder()
                .price(tripRequest.getPrice())
                .addressFrom(tripRequest.getAddressFrom())
                .addressTo(tripRequest.getAddressTo())
                .countSeats(tripRequest.getCountSeats())
                .timeTrip(tripRequest.getTimeTrip())
                .driverUsername(tripRequest.getDriverUsername())
                .car(car)
                .build();
    }

}
