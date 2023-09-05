package ru.krayseer.voyage.domain.dto.responses;

import lombok.Data;
import ru.krayseer.voyage.domain.dto.Response;

@Data
public class AuthResponse implements Response {

    private String username;

    private String token;

    private String role;

    private String password;

}
