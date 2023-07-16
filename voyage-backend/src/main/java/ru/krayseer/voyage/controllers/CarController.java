package ru.krayseer.voyage.controllers;

import org.springframework.http.HttpStatus;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;
import ru.krayseer.voyage.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.security.Principal;
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
    public List<CarResponse> getPersonCars(Principal principal) {
        return carService.loadUserCars(principal.getName());
    }

    @PostMapping
    public CarResponse createCar(@RequestBody @Valid CarRequest carRequest,
                                 Principal principal) {
        return carService.addUserCar(principal.getName(), carRequest);
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