package com.example.voyage.config;

import com.example.voyage.beans.phone.Realme;
import com.example.voyage.beans.phone.Xiaomi;
import com.example.voyage.beans.processor.Dimensity;
import com.example.voyage.beans.processor.Exynos;
import com.example.voyage.beans.processor.Snapdragon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {
    @Bean
    @Scope(value = "prototype")
    public Snapdragon snapdragon(){
        return new Snapdragon();
    }

    @Bean
    @Scope(value = "prototype")
    public Dimensity dimensity(){
        return new Dimensity();
    }

    @Bean
    @Scope(value = "prototype")
    public Exynos exynos(){
        return new Exynos();
    }
}
