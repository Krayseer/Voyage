package ru.krayseer.voyage.domain.dto.responses;

import lombok.Data;
import ru.krayseer.voyage.commons.constants.Role;
import ru.krayseer.voyageapi.domain.dto.Response;

@Data
public class AuthResponse implements Response {

    private String username;

    private String token;

    private Role role;

    private String password;

}
