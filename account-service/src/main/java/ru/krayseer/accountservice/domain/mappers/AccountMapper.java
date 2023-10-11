package ru.krayseer.accountservice.domain.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.krayseer.accountservice.commons.errors.EmailAlreadyExistsError;
import ru.krayseer.accountservice.commons.errors.PhoneNumberAlreadyExistsError;
import ru.krayseer.accountservice.commons.errors.UsernameAlreadyExistsError;
import ru.krayseer.accountservice.domain.dto.responses.AccountResponse;
import ru.krayseer.accountservice.domain.dto.responses.AuthResponse;
import ru.krayseer.accountservice.domain.dto.requests.RegisterRequest;
import ru.krayseer.accountservice.domain.entities.Account;
import ru.krayseer.accountservice.domain.repositories.AccountRepository;
import ru.krayseer.accountservice.services.RedisService;
import ru.krayseer.voyageapi.domain.dto.Request;
import ru.krayseer.voyageapi.domain.mapper.Mapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountMapper implements Mapper<Account> {

    private final RedisService redisService;

    private final PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;

    public AuthResponse createResponse(Account account) {
        String authToken = redisService.getUsernameToken(account.getUsername());
        return AuthResponse.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
                .token(authToken)
                .build();
    }

    public Account createEntity(Request request) {
        RegisterRequest registerRequest = (RegisterRequest) request;
        if (accountRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UsernameAlreadyExistsError();
        } else if (accountRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsError();
        } else if (accountRepository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsError();
        } else {
            return Account.builder()
                    .username(registerRequest.getUsername())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .name(registerRequest.getName())
                    .age(registerRequest.getAge())
                    .phoneNumber(registerRequest.getPhoneNumber())
                    .email(registerRequest.getEmail())
                    .createdAt(LocalDateTime.now())
                    .role(registerRequest.getRole())
                    .build();
        }
    }

    public AccountResponse createAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .name(account.getName())
                .age(account.getAge())
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .createdAt(account.getCreatedAt())
                .role(account.getRole().name())
                .build();
    }

}
