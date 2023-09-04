package ru.krayseer.voyage.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.dto.responses.AuthInfo;
import ru.krayseer.voyage.services.RemoteAccountService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class RemoteAccountServiceImpl implements RemoteAccountService {

    private static final String baseUrl = "http://localhost:8765/account-service/";

    private final RestTemplate restTemplate;

    @Override
    public String getAccountUsername(String authHeader) {
        var headers = new HttpHeaders();
        headers.set(AUTHORIZATION, authHeader);
        var url = baseUrl + "auth/username";
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
    }

    @Override
    public AuthInfo getAccountAuthInfo(String username) {
        var url = baseUrl + String.format("auth/auth-info?username=%s", username);
        return restTemplate.getForObject(url, AuthInfo.class);
    }

    @Override
    public AccountResponse getAccountInfo(String username) {
        var url = baseUrl + String.format("account/%s", username);
        return restTemplate.getForEntity(url, AccountResponse.class).getBody();
    }

}
