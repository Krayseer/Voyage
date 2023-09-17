package ru.krayseer.apigateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.krayseer.voyageapi.services.RemoteService;

@Service
@RequiredArgsConstructor
public class RemoteAuthenticationService implements RemoteService {

    private final RestTemplate restTemplate;

    public void validateToken(String header) {
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, header);
        var url = String.format("%s/auth/validate?authHeader=%s", baseUrl(), header);
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Boolean.class);
    }

    @Override
    public String baseUrl() {
        return "http://localhost:8765/account-service";
    }
}
