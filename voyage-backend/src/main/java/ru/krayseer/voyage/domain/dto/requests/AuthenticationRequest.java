package ru.krayseer.voyage.domain.dto.requests;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import ru.krayseer.voyage.domain.dto.Request;

@Data
@Builder
public class AuthenticationRequest implements Request {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
