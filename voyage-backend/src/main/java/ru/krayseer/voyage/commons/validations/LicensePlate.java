package ru.krayseer.voyage.commons.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.krayseer.voyageapi.commons.constants.RegExp;

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

class LicensePlateValidator implements ConstraintValidator<LicensePlate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.matches(RegExp.CAR_NUMBER);
    }

}
