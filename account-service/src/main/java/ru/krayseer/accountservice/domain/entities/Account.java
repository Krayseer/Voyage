package ru.krayseer.accountservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.krayseer.accountservice.commons.enums.Role;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Username аккаунта
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Пароль от аккаунта
     */
    private String password;

    /**
     * Имя человека
     */
    private String name;

    /**
     * Возраст человека
     */
    private Integer age;

    /**
     * Телефонный номер
     */
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    /**
     * Адрес электонной почты
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Дата и время создания аккаунта
     */
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Ссылка на аватарку профиля
     */
    private String avatarUrl;

    /**
     * Роль пользователя
     */
    @Enumerated(EnumType.STRING)
    private Role role;

}
