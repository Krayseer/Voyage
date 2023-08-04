package ru.krayseer.voyage.services.impl;

import lombok.extern.slf4j.Slf4j;
import ru.krayseer.voyage.commons.errors.CarIsPresentError;
import ru.krayseer.voyage.commons.errors.CarNotExistsError;
import ru.krayseer.voyage.commons.errors.AccountNotExistsError;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;
import ru.krayseer.voyage.domain.entities.Account;
import ru.krayseer.voyage.domain.entities.Car;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.domain.repositories.CarRepository;
import ru.krayseer.voyage.domain.repositories.TripRepository;
import ru.krayseer.voyage.services.CarService;
import ru.krayseer.voyage.utils.dto.CarDtoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final AccountRepository accountRepository;

    private final TripRepository tripRepository;

    private final CarDtoFactory carFactory;

    @Override
    public CarResponse loadCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(CarNotExistsError::new);
        log.info("Load car with id: {}", id);
        return carFactory.createResponse(car);
    }

    @Override
    public List<CarResponse> loadUserCars(String username) {
        Account account = accountRepository.findByUsername(username).orElseThrow(AccountNotExistsError::new);
        log.info("Load \"{}\" cars", username);
        return carRepository.findCarsByAccount(account).stream().map(carFactory::createResponse).toList();
    }

    @Override
    public CarResponse addUserCar(String username, CarRequest carRequest) {
        carRequest.setAccountUsername(username);
        Car car = carFactory.createObjectFrom(carRequest);
        carRepository.save(car);
        log.info("Add car for user: {}", username);
        return carFactory.createResponse(car);
    }

    @Override
    public CarResponse updateCar(Long carId, CarRequest carRequest) {
        Car car = carRepository.findById(carId).orElseThrow(CarNotExistsError::new);
        car.setMark(carRequest.getMark());
        car.setColor(carRequest.getColor());
        car.setLicensePlate(carRequest.getLicensePlate());
        carRepository.save(car);
        log.info("Update \"{}\" car with id {}", car.getAccount().getId(), car.getId());
        return carFactory.createResponse(car);
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
