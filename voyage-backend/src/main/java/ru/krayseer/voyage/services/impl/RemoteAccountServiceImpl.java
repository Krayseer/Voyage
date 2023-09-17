package ru.krayseer.voyage.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.dto.responses.AuthResponse;
import ru.krayseer.voyage.services.RemoteAccountService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class RemoteAccountServiceImpl implements RemoteAccountService {

    private final RestTemplate restTemplate;

    @Override
    public String getAccountUsername(String authHeader) {
        var headers = new HttpHeaders();
        headers.set(AUTHORIZATION, authHeader);
        var url = String.format("%s/auth/username", baseUrl());
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
    }

    @Override
    public AuthResponse getAccountAuthInfo(String username) {
        var url = String.format("%s/auth/auth-info?username=%s", baseUrl(), username);
        return restTemplate.getForObject(url, AuthResponse.class);
    }

    @Override
    public AccountResponse getAccountInfo(String username) {
        var url = String.format("%s/account/%s", baseUrl(), username);
        return restTemplate.getForEntity(url, AccountResponse.class).getBody();
    }

    @Override
    public String baseUrl() {
        return "http://localhost:8765/account-service";
    }

}
