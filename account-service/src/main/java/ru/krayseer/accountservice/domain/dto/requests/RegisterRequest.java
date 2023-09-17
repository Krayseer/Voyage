package ru.krayseer.accountservice.domain.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.krayseer.accountservice.commons.constants.Role;
import ru.krayseer.accountservice.commons.validations.EmailAddress;
import ru.krayseer.accountservice.commons.validations.PhoneNumber;
import ru.krayseer.voyageapi.domain.dto.Request;

@Data
public class RegisterRequest implements Request {

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

    /**
     * Имя
     */
    @NotBlank(message = "you need to enter name")
    private String name;

    /**
     * Возраст
     */
    @NotNull(message = "you need to enter age")
    private Integer age;

    /**
     * Телефонный номер
     */
    @PhoneNumber
    private String phoneNumber;

    /**
     * Электронная почта
     */
    @EmailAddress
    private String email;

    /**
     * Роль
     */
    private Role role;

}
