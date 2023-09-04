package ru.krayseer.accountservice.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.accountservice.commons.enums.Role;
import ru.krayseer.accountservice.domain.dto.Response;

@Data
@Builder
public class AuthResponse implements Response {

    private String username;

    private Role role;

    private String password;

    private String token;

}