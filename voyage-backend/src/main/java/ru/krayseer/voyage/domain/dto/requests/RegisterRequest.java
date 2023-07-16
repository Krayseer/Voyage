package ru.krayseer.voyage.domain.dto.requests;

import ru.krayseer.voyage.commons.constants.Role;
import ru.krayseer.voyage.commons.validations.interfaces.EmailAddress;
import ru.krayseer.voyage.commons.validations.interfaces.PhoneNumber;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import ru.krayseer.voyage.domain.dto.Request;

@Data
@Builder
public class RegisterRequest implements Request {

    @NotBlank(message = "нужно ввести username")
    private String username;

    @NotBlank(message = "нужно ввести пароль")
    private String password;

    @NotBlank(message = "нужно ввести имя")
    private String name;

    @NotNull(message = "нужно ввести возраст")
    private Integer age;

    @PhoneNumber
    private String phoneNumber;

    @EmailAddress
    private String email;

    private Role role;

}
