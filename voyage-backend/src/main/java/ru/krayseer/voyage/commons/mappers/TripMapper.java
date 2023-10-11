package ru.krayseer.voyage.commons.mappers;

import org.springframework.stereotype.Component;
import ru.krayseer.voyage.errors.CarNotExistsError;
import ru.krayseer.voyage.domain.requests.TripRequest;
import ru.krayseer.voyage.domain.responses.FollowerResponse;
import ru.krayseer.voyage.domain.responses.TripResponse;
import ru.krayseer.voyage.domain.Trip;
import lombok.RequiredArgsConstructor;
import ru.krayseer.voyage.repositories.CarRepository;
import ru.krayseer.voyageapi.domain.Request;
import ru.krayseer.voyageapi.commons.constants.mapper.Mapper;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TripMapper implements Mapper<Trip> {

    private final CarRepository carRepository;

    private final FollowerMapper followerMapper;

    @Override
    public TripResponse createResponse(Trip trip) {
        List<FollowerResponse> tripFollowers = trip.getFollowers() == null ? Collections.emptyList() :
                trip.getFollowers().stream()
                        .map(followerMapper::createResponse)
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
    public Trip createEntity(Request request) {
        TripRequest tripRequest = (TripRequest) request;
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
