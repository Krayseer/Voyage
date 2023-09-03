package ru.krayseer.accountservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.krayseer.accountservice.commons.enums.Role;
import ru.krayseer.accountservice.domain.dto.requests.AuthRequest;
import ru.krayseer.accountservice.domain.dto.responses.AuthResponse;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;
import ru.krayseer.accountservice.domain.mappers.AccountMapper;
import ru.krayseer.accountservice.domain.repositories.AccountRepository;
import ru.krayseer.accountservice.services.AuthenticationService;
import ru.krayseer.accountservice.services.jwt.JwtService;

import static ru.krayseer.accountservice.commons.enums.Role.ROLE_ADMIN;
import static ru.krayseer.accountservice.commons.enums.Role.ROLE_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final AccountMapper accountMapper;

    private final UserDetailsService userDetailsService;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return accountMapper.createResponse(request.getUsername());
    }

    public AuthResponse registerUser(RegisterRequest request) {
        return createAccount(request, ROLE_USER);
    }

    public AuthResponse registerAdmin(RegisterRequest request, Integer secret) {
//        if (!Objects.equals(secret, .getSecretAdmin())) {
//            throw new RuntimeException("invalid secret admin key");
//        }
        return createAccount(request, ROLE_ADMIN);
    }

    private AuthResponse createAccount(RegisterRequest request, Role role) {
        request.setRole(role);
        var account = accountMapper.createEntity(request);
        accountRepository.save(account);
        return accountMapper.createResponse(account.getUsername());
    }

    public void validateToken(String token) {
//        var username = jwtService.extractUsername(token);
        log.info("validate token");
//        jwtService.isTokenValid(token, userDetailsService.loadUserByUsername(username));
    }

}
