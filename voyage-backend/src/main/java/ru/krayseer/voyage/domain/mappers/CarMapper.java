package ru.krayseer.voyage.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;
import ru.krayseer.voyage.domain.entities.Car;
import ru.krayseer.voyageapi.domain.dto.Request;
import ru.krayseer.voyageapi.domain.mapper.Mapper;

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
