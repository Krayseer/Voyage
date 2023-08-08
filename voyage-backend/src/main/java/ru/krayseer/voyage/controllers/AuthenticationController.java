package ru.krayseer.voyage.controllers;

import jakarta.validation.Valid;
import ru.krayseer.voyage.domain.dto.requests.AuthenticationRequest;
import ru.krayseer.voyage.domain.dto.requests.RegisterRequest;
import ru.krayseer.voyage.domain.dto.responses.AuthResponse;
import ru.krayseer.voyage.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) {
        return authenticationService.registerUser(request);
    }

    @PostMapping("/admin")
    public AuthResponse createAdmin(@RequestBody @Valid RegisterRequest request,
                                    @RequestParam Integer secret) {
        return authenticationService.registerAdmin(request, secret);
    }

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

}
