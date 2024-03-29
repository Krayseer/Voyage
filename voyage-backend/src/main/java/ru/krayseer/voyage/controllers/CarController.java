package ru.krayseer.voyage.controllers;

import org.springframework.http.HttpStatus;
import ru.krayseer.voyageapi.domain.dto.Response;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;
import ru.krayseer.voyage.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ru.krayseer.voyage.utils.SecurityUtils;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    public Response getCarById(@PathVariable Long id) {
        return carService.loadCar(id);
    }

    @GetMapping
    public List<CarResponse> getPersonCars() {
        return carService.loadUserCars(SecurityUtils.getUsername());
    }

    @PostMapping
    public Response createCar(@RequestBody @Valid CarRequest carRequest) {
        return carService.addUserCar(SecurityUtils.getUsername(), carRequest);
    }

    @PutMapping("/{id}")
    public Response updateCar(@RequestBody @Valid CarRequest carRequest,
                              @PathVariable Long id) {
        return carService.updateCar(id, carRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

}
