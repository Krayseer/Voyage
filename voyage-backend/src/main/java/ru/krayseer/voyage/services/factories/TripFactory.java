package ru.krayseer.voyage.services.factories;

import org.springframework.stereotype.Component;
import ru.krayseer.voyage.commons.exceptions.CarNotExistsError;
import ru.krayseer.voyage.commons.exceptions.AccountNotExistsError;
import ru.krayseer.voyage.domain.dto.requests.TripRequest;
import ru.krayseer.voyage.domain.dto.responses.FollowerResponse;
import ru.krayseer.voyage.domain.dto.responses.TripResponse;
import ru.krayseer.voyage.domain.entities.Trip;
import lombok.RequiredArgsConstructor;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.domain.repositories.CarRepository;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TripFactory extends BaseFactory<Trip, TripRequest> {

    private final AccountRepository accountRepository;

    private final CarRepository carRepository;

    @Override
    public TripResponse createResponse(Trip trip) {
        List<FollowerResponse> tripFollowers = trip.getFollowers() == null ? Collections.emptyList() : trip.getFollowers()
                .stream()
                .map(follower -> FollowerResponse
                        .builder()
                        .username(follower.getAccount().getUsername())
                        .name(follower.getAccount().getName())
                        .phoneNumber(follower.getAccount().getPhoneNumber())
                        .build())
                .toList();
        return TripResponse
                .builder()
                .id(trip.getId())
                .driverId(trip.getDriver().getId())
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
    public Trip createObjectFrom(TripRequest tripRequest) {
        var account = accountRepository.findByUsername(tripRequest.getDriverUsername()).orElseThrow(AccountNotExistsError::new);
        var car = carRepository.findById(tripRequest.getCarId()).orElseThrow(CarNotExistsError::new);
        return Trip
                .builder()
                .price(tripRequest.getPrice())
                .addressFrom(tripRequest.getAddressFrom())
                .addressTo(tripRequest.getAddressTo())
                .countSeats(tripRequest.getCountSeats())
                .timeTrip(tripRequest.getTimeTrip())
                .driver(account)
                .car(car)
                .build();
    }

}
