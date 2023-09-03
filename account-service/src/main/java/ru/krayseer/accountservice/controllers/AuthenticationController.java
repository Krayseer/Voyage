package ru.krayseer.accountservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.krayseer.accountservice.commons.utils.Utils;
import ru.krayseer.accountservice.domain.dto.requests.AuthRequest;
import ru.krayseer.accountservice.domain.dto.responses.AuthResponse;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;
import ru.krayseer.accountservice.services.AuthenticationService;
import ru.krayseer.accountservice.services.jwt.JwtService;

import java.security.Principal;
import java.util.PrimitiveIterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    @GetMapping("/validate")
    public void validateToken() {}

    @GetMapping("/username")
    public String getUsernameFromToken(HttpServletRequest request) {
        return jwtService.extractUsername(Utils.getTokenFromHeader(request));
    }

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
    public AuthResponse authenticate(@RequestBody @Valid AuthRequest request) {
        return authenticationService.authenticate(request);
    }

}