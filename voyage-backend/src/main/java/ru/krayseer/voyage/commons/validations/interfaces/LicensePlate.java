package ru.krayseer.voyage.commons.validations.interfaces;

import ru.krayseer.voyage.commons.validations.LicensePlateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LicensePlateValidator.class)
public @interface LicensePlate {

    String message() default "неверно введён номер автомобиля";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
