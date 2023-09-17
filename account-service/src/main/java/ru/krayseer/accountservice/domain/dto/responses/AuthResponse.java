package ru.krayseer.accountservice.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.accountservice.commons.constants.Role;
import ru.krayseer.voyageapi.domain.dto.Response;

@Data
@Builder
public class AuthResponse implements Response {

    /**
     * Пользователь
     */
    private String username;

    /**
     * Роль
     */
    private Role role;

    /**
     * Пароль
     */
    private String password;

    /**
     * Jwt токен авторизации
     */
    private String token;

}