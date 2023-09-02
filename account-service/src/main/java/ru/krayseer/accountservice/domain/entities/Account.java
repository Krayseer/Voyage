package ru.krayseer.accountservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.krayseer.accountservice.commons.enums.Role;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
