package ru.krayseer.photostorage;

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
     * Место хранения фотографий
     */
    private String storagePath;

}
