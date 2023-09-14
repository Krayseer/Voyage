package ru.krayseer.accountservice.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.accountservice.domain.dto.Response;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountResponse implements Response {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Пользователь
     */
    private String username;

    /**
     * Имя
     */
    private String name;

    /**
     * Возраст
     */
    private Integer age;

    /**
     * Телефонный номер
     */
    private String phoneNumber;

    /**
     * Электронная почта
     */
    private String email;

    /**
     * Время создания аккаунта
     */
    private LocalDateTime createdAt;

    /**
     * Роль
     */
    private String role;

}
