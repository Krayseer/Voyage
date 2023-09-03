package ru.krayseer.accountservice.commons.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LogControllerAdvice {

    private final HttpServletRequest request;

    @Before("execution(* ru.krayseer.accountservice.controllers.*Controller.*(..))")
    public void logBefore() {
        var endpoint = request.getRequestURI();
        log.info("START endpoint: {}", endpoint);
    }

    @AfterReturning("execution(* ru.krayseer.accountservice.controllers.*Controller.*(..))")
    public void logAfter() {
        log.info("END endpoint: {}", request.getRequestURI());
    }

}
