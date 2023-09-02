package ru.krayseer.voyage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import ru.krayseer.voyage.commons.utils.Utils;
import ru.krayseer.voyage.domain.dto.requests.TripRequest;
import ru.krayseer.voyage.domain.dto.responses.FollowerResponse;
import ru.krayseer.voyage.domain.dto.responses.TripResponse;
import ru.krayseer.voyage.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

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
    public TripResponse createNewTrip(@RequestBody @Valid TripRequest tripRequest,
                                      HttpServletRequest request) {
        return tripService.createNewTrip(Utils.getTokenFromHeader(request), tripRequest);
    }

    @PostMapping("/follower/{id}")
    public FollowerResponse subscribeOnTrip(@PathVariable Long id,
                                            HttpServletRequest request) {
        return tripService.subscribeFollowerOnTrip(id, Utils.getTokenFromHeader(request));
    }

    @PutMapping("/{id}")
    public TripResponse updateTrip(@RequestBody @Valid TripRequest tripRequest,
                                   @PathVariable Long id) {
        return tripService.updateTrip(id, tripRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

}
