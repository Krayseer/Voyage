package ru.krayseer.voyage.services.impl;

import lombok.extern.slf4j.Slf4j;
import ru.krayseer.voyage.commons.errors.CarIsPresentError;
import ru.krayseer.voyage.commons.errors.CarNotExistsError;
import ru.krayseer.voyageapi.domain.dto.Response;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;
import ru.krayseer.voyage.domain.entities.Car;
import ru.krayseer.voyage.domain.repositories.CarRepository;
import ru.krayseer.voyage.domain.repositories.TripRepository;
import ru.krayseer.voyage.services.CarService;
import ru.krayseer.voyage.domain.mappers.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final TripRepository tripRepository;

    private final CarMapper carMapper;

    @Override
    public Response loadCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(CarNotExistsError::new);
        log.info("Load car with id: {}", id);
        return carMapper.createResponse(car);
    }

    @Override
    public List<CarResponse> loadUserCars(String username) {
        log.info("Load \"{}\" cars", username);
        return carRepository.findCarsByAccountUsername(username).stream()
                .map(carMapper::createResponse)
                .toList();
    }

    @Override
    public Response addUserCar(String username, CarRequest carRequest) {
        carRequest.setAccountUsername(username);
        Car car = carMapper.createEntity(carRequest);
        carRepository.save(car);
        log.info("Add car for user: {}", username);
        return carMapper.createResponse(car);
    }

    @Override
    public Response updateCar(Long carId, CarRequest carRequest) {
        Car car = carRepository.findById(carId).orElseThrow(CarNotExistsError::new);
        car.setMark(carRequest.getMark());
        car.setColor(carRequest.getColor());
        car.setLicensePlate(carRequest.getLicensePlate());
        carRepository.save(car);
        log.info("Update \"{}\" car with id {}", car.getAccountUsername(), car.getId());
        return carMapper.createResponse(car);
    }

    @Override
    public void deleteCar(Long carId) {
        var car = carRepository.findById(carId).orElseThrow(CarNotExistsError::new);
        if (tripRepository.findTripByCar(car).isPresent()) {
            throw new CarIsPresentError();
        }
        log.info("Delete car by id {}", carId);
        carRepository.deleteById(carId);
    }

}
