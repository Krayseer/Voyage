package ru.krayseer.voyage.commons.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krayseer.voyage.domain.requests.CarRequest;
import ru.krayseer.voyage.domain.responses.CarResponse;
import ru.krayseer.voyage.domain.Car;
import ru.krayseer.voyageapi.domain.Request;
import ru.krayseer.voyageapi.commons.constants.mapper.Mapper;

@Service
@RequiredArgsConstructor
public class CarMapper implements Mapper<Car> {

    @Override
    public CarResponse createResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .accountUsername(car.getAccountUsername())
                .mark(car.getMark())
                .color(car.getColor())
                .licensePlate(car.getLicensePlate())
                .build();
    }

    @Override
    public Car createEntity(Request request) {
        CarRequest carRequest = (CarRequest) request;
        return Car.builder()
                .mark(carRequest.getMark())
                .color(carRequest.getColor())
                .licensePlate(carRequest.getLicensePlate())
                .accountUsername(carRequest.getAccountUsername())
                .build();
    }

}
