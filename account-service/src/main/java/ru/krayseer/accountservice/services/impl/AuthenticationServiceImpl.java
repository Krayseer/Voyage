package ru.krayseer.accountservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.krayseer.accountservice.commons.constants.Role;
import ru.krayseer.accountservice.domain.dto.Response;
import ru.krayseer.accountservice.domain.dto.requests.AuthRequest;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;
import ru.krayseer.accountservice.domain.entities.Account;
import ru.krayseer.accountservice.domain.mappers.AccountMapper;
import ru.krayseer.accountservice.domain.repositories.AccountRepository;
import ru.krayseer.accountservice.services.AuthenticationService;
import ru.krayseer.accountservice.services.jwt.JwtService;

import static ru.krayseer.accountservice.commons.constants.Role.ADMIN;
import static ru.krayseer.accountservice.commons.constants.Role.USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final JwtService jwtService;

    public Response authenticate(AuthRequest request) {
        return accountMapper.createResponse(request.getUsername());
    }

    @Override
    public String getUsernameFromToken(String token) {
        return token == null ? null : jwtService.extractUsername(token);
    }

    public Response registerUser(RegisterRequest request) {
        return createAccount(request, USER);
    }

    public Response registerAdmin(RegisterRequest request, String secret) {
//        if (!Objects.equals(secret, .getSecretAdmin())) {
//            throw new RuntimeException("invalid secret admin key");
//        }
        return createAccount(request, ADMIN);
    }

    private Response createAccount(RegisterRequest request, Role role) {
        request.setRole(role);
        Account account = accountMapper.createEntity(request);
        accountRepository.save(account);
        return accountMapper.createResponse(account.getUsername());
    }

    public boolean validateToken(String header) {
        String token = header.substring(7);
        return jwtService.isTokenValid(token);
    }

    public Response getAccountAuthInfo(String username) {
        return accountMapper.createResponse(username);
    }

}
