package ru.krayseer.voyage.utils.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.krayseer.voyage.commons.errors.EmailAlreadyExistsError;
import ru.krayseer.voyage.commons.errors.PhoneNumberAlreadyExistsError;
import ru.krayseer.voyage.commons.errors.UsernameAlreadyExistsError;
import ru.krayseer.voyage.domain.dto.requests.RegisterRequest;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.dto.responses.AuthResponse;
import ru.krayseer.voyage.domain.entities.Account;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.services.security.JwtService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountDtoFactory extends BaseDtoFactory<Account, RegisterRequest> {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthResponse createResponse(Account account) {
        return AuthResponse
                .builder()
                .token(jwtService.generateToken(account))
                .build();
    }

    public Account createObjectFrom(RegisterRequest accountRequest) {
        if (accountRepository.existsByUsername(accountRequest.getUsername())) {
            throw new UsernameAlreadyExistsError();
        } else if (accountRepository.existsByEmail(accountRequest.getEmail())) {
            throw new EmailAlreadyExistsError();
        } else if (accountRepository.existsByPhoneNumber(accountRequest.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsError();
        } else {
            return Account
                    .builder()
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
        return AccountResponse
                .builder()
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
