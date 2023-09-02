package ru.krayseer.apigateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RemoteAuthenticationService {

    private final RestTemplate restTemplate;

    public void validateToken(String token) {
        restTemplate.getForObject("http://account-service//auth//validate?token" + token, String.class);
    }

}
