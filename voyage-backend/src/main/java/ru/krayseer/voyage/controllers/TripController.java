package ru.krayseer.voyage.controllers;

import ru.krayseer.voyageapi.domain.Response;
import ru.krayseer.voyage.domain.requests.TripRequest;
import ru.krayseer.voyage.domain.responses.TripResponse;
import ru.krayseer.voyage.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ru.krayseer.voyage.utils.SecurityUtils;

import java.util.List;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping
    public List<TripResponse> getAllTrips() {
        return tripService.loadAllTrips();
    }

    @PostMapping
    public Response createNewTrip(@RequestBody @Valid TripRequest tripRequest) {
        return tripService.createNewTrip(SecurityUtils.getUsername(), tripRequest);
    }

    @PostMapping("/follower/{id}")
    public Response subscribeOnTrip(@PathVariable Long id) {
        return tripService.subscribeFollowerOnTrip(id, SecurityUtils.getUsername());
    }

    @PutMapping("/{id}")
    public Response updateTrip(@RequestBody @Valid TripRequest tripRequest,
                               @PathVariable Long id) {
        return tripService.updateTrip(id, tripRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

}
