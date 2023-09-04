package ru.krayseer.accountservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.krayseer.accountservice.commons.utils.Utils;
import ru.krayseer.accountservice.domain.dto.Response;
import ru.krayseer.accountservice.domain.dto.requests.AuthRequest;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;
import ru.krayseer.accountservice.services.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String authHeader) {
        return authenticationService.validateToken(authHeader);
    }

    @GetMapping("/auth-info")
    public Response getAccountAuthInfo(@RequestParam String username) {
        return authenticationService.getAccountAuthInfo(username);
    }

    @GetMapping("/username")
    public String getUsernameFromToken(HttpServletRequest request) {
        return authenticationService.getUsernameFromToken(Utils.getTokenFromHeader(request));
    }

    @PostMapping("/register")
    public Response register(@RequestBody @Valid RegisterRequest request) {
        return authenticationService.registerUser(request);
    }

    @PostMapping("/admin")
    public Response createAdmin(@RequestBody @Valid RegisterRequest request,
                                @RequestParam Integer secret) {
        return authenticationService.registerAdmin(request, secret);
    }

    @PostMapping("/authenticate")
    public Response authenticate(@RequestBody @Valid AuthRequest request) {
        return authenticationService.authenticate(request);
    }

}
