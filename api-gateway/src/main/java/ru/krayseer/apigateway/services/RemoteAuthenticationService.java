package ru.krayseer.apigateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RemoteAuthenticationService {

    private final RestTemplate restTemplate;

    public void validateToken(String header) {
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, header);
        var url = String.format("http://localhost:8765/account-service/auth/validate?authHeader=%s", header);
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Boolean.class);
    }

}
