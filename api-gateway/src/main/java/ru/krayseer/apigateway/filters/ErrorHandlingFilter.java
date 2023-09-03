package ru.krayseer.apigateway.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.krayseer.apigateway.errors.BaseError;
import ru.krayseer.apigateway.errors.ErrorResponse;

@Component
@RequiredArgsConstructor
public class ErrorHandlingFilter implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @NotNull
    @Override
    @SneakyThrows
    public Mono<Void> handle(@NotNull ServerWebExchange exchange,
                             @NotNull Throwable ex) {
        if (ex instanceof BaseError) {
            var errorResponse = new ErrorResponse(
                    ex.getMessage(), ((BaseError) ex).getErrorCode()
            );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return exchange.getResponse().writeWith(
                    Mono.just(
                            exchange.getResponse()
                                    .bufferFactory()
                                    .wrap(objectMapper.writeValueAsBytes(errorResponse))
                    )
            );
        } else {
            return null;
        }
    }
}