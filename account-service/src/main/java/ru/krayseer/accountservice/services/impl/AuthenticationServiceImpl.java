package ru.krayseer.accountservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.krayseer.accountservice.ApplicationConfig;
import ru.krayseer.accountservice.commons.constants.Role;
import ru.krayseer.accountservice.commons.errors.InvalidAdminSecretKeyError;
import ru.krayseer.accountservice.commons.errors.UsernameNotFoundError;
import ru.krayseer.accountservice.domain.dto.requests.AuthRequest;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;
import ru.krayseer.accountservice.domain.dto.responses.AuthResponse;
import ru.krayseer.accountservice.domain.entities.Account;
import ru.krayseer.accountservice.domain.mappers.AccountMapper;
import ru.krayseer.accountservice.domain.repositories.AccountRepository;
import ru.krayseer.accountservice.services.AuthenticationService;
import ru.krayseer.accountservice.services.jwt.JwtService;
import ru.krayseer.voyageapi.domain.dto.Response;

import java.util.Objects;

import static ru.krayseer.accountservice.commons.constants.Role.ADMIN;
import static ru.krayseer.accountservice.commons.constants.Role.USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;

    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;

    private final ApplicationConfig applicationConfig;

    public AuthResponse authenticate(AuthRequest request) {
        Account account = accountRepository.findByUsername(request.getUsername()).orElseThrow(UsernameNotFoundError::new);
        return accountMapper.createResponse(account);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return token == null ? null : jwtService.extractUsername(token);
    }

    public AuthResponse registerUser(RegisterRequest request) {
        request.setRole(USER);
        return createAccount(request);
    }

    public AuthResponse registerAdmin(RegisterRequest request, String secret) {
        if (!Objects.equals(secret, applicationConfig.getSecretAdmin())) {
            throw new InvalidAdminSecretKeyError();
        }
        request.setRole(ADMIN);
        return createAccount(request);
    }

    private AuthResponse createAccount(RegisterRequest request) {
        Account account = accountMapper.createEntity(request);
        accountRepository.save(account);
        return accountMapper.createResponse(account);
    }

    public boolean validateToken(String header) {
        String token = header.substring(7);
        return jwtService.isTokenValid(token);
    }

    public Response getAccountAuthInfo(String username) {
        Account account = accountRepository.findByUsername(username).orElseThrow(UsernameNotFoundError::new);
        return accountMapper.createResponse(account);
    }

}
