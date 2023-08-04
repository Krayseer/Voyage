package ru.krayseer.voyage.utils.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krayseer.voyage.commons.errors.AccountNotExistsError;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;
import ru.krayseer.voyage.domain.entities.Account;
import ru.krayseer.voyage.domain.entities.Car;
import ru.krayseer.voyage.domain.repositories.AccountRepository;

@Service
@RequiredArgsConstructor
public class CarDtoFactory extends BaseDtoFactory<Car, CarRequest> {

    private final AccountRepository accountRepository;

    @Override
    public CarResponse createResponse(Car car) {
        return CarResponse
                .builder()
                .id(car.getId())
                .accountUsername(car.getAccount().getUsername())
                .mark(car.getMark())
                .color(car.getColor())
                .licensePlate(car.getLicensePlate())
                .build();
    }

    @Override
    public Car createObjectFrom(CarRequest car) {
        Account account = accountRepository.findByUsername(car.getAccountUsername())
                .orElseThrow(AccountNotExistsError::new);
        return Car
                .builder()
                .mark(car.getMark())
                .color(car.getColor())
                .licensePlate(car.getLicensePlate())
                .account(account)
                .build();
    }

}
