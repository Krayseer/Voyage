package ru.krayseer.accountservice.domain.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.krayseer.accountservice.domain.dto.Request;

@Data
public class AuthRequest implements Request {

    /**
     * Пользователь
     */
    @NotBlank(message = "you need to enter username")
    private String username;

    /**
     * Пароль
     */
    @NotBlank(message = "you need to enter password")
    private String password;

}
