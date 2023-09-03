package ru.krayseer.voyage.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.services.RemoteAccountService;

@Service
@RequiredArgsConstructor
public class RemoteAccountServiceImpl implements RemoteAccountService {

    private final RestTemplate restTemplate;

    @Override
    public String getAccountUsername(String authHeader) {
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authHeader);
        var url = "http://localhost:8765/account-service/auth/username";
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
    }

    @Override
    public AccountResponse getAccountInfo(String username) {
        var url = String.format("http://localhost:8765/account-service/account/%s", username);
        return restTemplate.getForEntity(url, AccountResponse.class).getBody();
    }

}
