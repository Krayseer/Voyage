package ru.krayseer.voyage.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;
import ru.krayseer.voyage.domain.entities.Car;

@Service
@RequiredArgsConstructor
public class CarMapper implements Mapper<Car, CarRequest> {

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
    public Car createEntity(CarRequest car) {
        return Car.builder()
                .mark(car.getMark())
                .color(car.getColor())
                .licensePlate(car.getLicensePlate())
                .accountUsername(car.getAccountUsername())
                .build();
    }

}
