package ru.krayseer.apigateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RemoteAuthenticationService {

    private final RestTemplate restTemplate;

    public void validateToken(String header) {
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, header);
        var url = "http://localhost:8765/account-service/auth/validate";
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ResponseEntity.class);
    }

}
