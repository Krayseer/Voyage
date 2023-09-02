package ru.krayseer.voyage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import ru.krayseer.voyage.commons.utils.Utils;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;
import ru.krayseer.voyage.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    public CarResponse getCarById(@PathVariable Long id) {
        return carService.loadCar(id);
    }

    @GetMapping
    public List<CarResponse> getPersonCars(HttpServletRequest request) {
        return carService.loadUserCars(Utils.getTokenFromHeader(request));
    }

    @PostMapping
    public CarResponse createCar(@RequestBody @Valid CarRequest carRequest,
                                 HttpServletRequest request) {
        return carService.addUserCar(Utils.getTokenFromHeader(request), carRequest);
    }

    @PutMapping("/{id}")
    public CarResponse updateCar(@PathVariable Long id,
                                 @RequestBody @Valid CarRequest carRequest) {
        return carService.updateCar(id, carRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

}
