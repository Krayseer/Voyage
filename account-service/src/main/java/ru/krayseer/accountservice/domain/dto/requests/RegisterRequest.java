package ru.krayseer.accountservice.domain.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.krayseer.accountservice.commons.enums.Role;
import ru.krayseer.accountservice.commons.validations.EmailAddress;
import ru.krayseer.accountservice.commons.validations.PhoneNumber;

@Data
@Builder
public class RegisterRequest {

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
