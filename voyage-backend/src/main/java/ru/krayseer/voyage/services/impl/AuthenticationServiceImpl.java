package ru.krayseer.voyage.services.impl;

import ru.krayseer.voyage.ApplicationProperties;
import ru.krayseer.voyage.commons.constants.Role;
import ru.krayseer.voyage.domain.dto.requests.AuthenticationRequest;
import ru.krayseer.voyage.domain.dto.requests.RegisterRequest;
import ru.krayseer.voyage.domain.dto.responses.AuthResponse;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.krayseer.voyage.domain.mappers.AccountMapper;

import java.util.Objects;

import static ru.krayseer.voyage.commons.constants.Role.ROLE_ADMIN;
import static ru.krayseer.voyage.commons.constants.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final AccountMapper accountMapper;

    private final ApplicationProperties applicationProperties;

    public AuthResponse registerUser(RegisterRequest request) {
        return createAccount(request, ROLE_USER);
    }

    public AuthResponse registerAdmin(RegisterRequest request, Integer secret) {
        if (!Objects.equals(secret, applicationProperties.getSecretAdmin())) {
            throw new RuntimeException("invalid secret admin key");
        }
        return createAccount(request, ROLE_ADMIN);
    }

    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var account = accountRepository.findByUsername(request.getUsername()).orElseThrow();
        return accountMapper.createResponse(account);
    }

    /**
     * Создать аккаунт
     * @param request данные об аккаунте
     * @param role роль аккаунта
     * @return jwt токен аккаунта
     */
    private AuthResponse createAccount(RegisterRequest request, Role role) {
        request.setRole(role);
        var account = accountMapper.createEntity(request);
        accountRepository.save(account);
        return accountMapper.createResponse(account);
    }

}
