package ru.krayseer.voyage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {

    private String photoServiceUrl;

    private String secretKey;

    private Integer secretAdmin;

}
