package ru.krayseer.accountservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.krayseer.accountservice.domain.dto.requests.AuthRequest;
import ru.krayseer.accountservice.domain.dto.responses.AuthResponse;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;
import ru.krayseer.accountservice.services.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) {
        return authenticationService.registerUser(request);
    }
//
    @PostMapping("/admin")
    public AuthResponse createAdmin(@RequestBody @Valid RegisterRequest request,
                                    @RequestParam Integer secret) {
        return authenticationService.registerAdmin(request, secret);
    }
//
    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody @Valid AuthRequest request) {
        return authenticationService.authenticate(request);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authenticationService.validateToken(token);
        return "Token is valid";
    }

}
