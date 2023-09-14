package ru.krayseer.accountservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {

    /**
     * Секретный ключ для создания администратора
     */
    private String secretAdmin;

    /**
     * Секретный ключ (JWT)
     */
    private String secretKey;

    /**
     * Время жизни токена
     */
    private Long tokenLifeCycle;

}
