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
import ru.krayseer.voyageapi.domain.dto.Response;
import ru.krayseer.voyageapi.domain.mapper.Mapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountMapper implements Mapper<Account, RegisterRequest> {

    private final RedisService redisService;

    private final PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;

    public Response createResponse(Account account) {
        return AuthResponse.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
                .token(redisService.getUsernameToken(account.getUsername()))
                .build();
    }

    public Account createEntity(RegisterRequest accountRequest) {
        if (accountRepository.existsByUsername(accountRequest.getUsername())) {
            throw new UsernameAlreadyExistsError();
        } else if (accountRepository.existsByEmail(accountRequest.getEmail())) {
            throw new EmailAlreadyExistsError();
        } else if (accountRepository.existsByPhoneNumber(accountRequest.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsError();
        } else {
            return Account.builder()
                    .username(accountRequest.getUsername())
                    .password(passwordEncoder.encode(accountRequest.getPassword()))
                    .name(accountRequest.getName())
                    .age(accountRequest.getAge())
                    .phoneNumber(accountRequest.getPhoneNumber())
                    .email(accountRequest.getEmail())
                    .createdAt(LocalDateTime.now())
                    .role(accountRequest.getRole())
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
