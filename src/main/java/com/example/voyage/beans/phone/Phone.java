package com.example.voyage.beans.phone;

import com.example.voyage.beans.processor.Processor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@RequiredArgsConstructor
public abstract class Phone {
    @PostConstruct
    public void postConstruct(){
        log.info("Создал телефон " + this.getClass().getSimpleName());
    }

    @PreDestroy
    public void preDestroy(){
        log.info("Удалил телефон " + this.getClass().getSimpleName());
    }
}
