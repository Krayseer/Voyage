package ru.krayseer.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import ru.krayseer.apigateway.errors.BaseError;
import ru.krayseer.apigateway.services.RemoteAuthenticationService;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator routeValidator;

    private final RemoteAuthenticationService remoteAuthenticationService;

    public AuthenticationFilter(RemoteAuthenticationService remoteAuthenticationService,
                                RouteValidator routeValidator) {
        super(Config.class);
        this.remoteAuthenticationService = remoteAuthenticationService;
        this.routeValidator = routeValidator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    System.out.println("im here");
                    return chain.filter(exchange);
                }
                try {
                    var header = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    remoteAuthenticationService.validateToken(header);
                } catch (HttpClientErrorException exception) {
                    throw new BaseError(exception);
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config { }

}
