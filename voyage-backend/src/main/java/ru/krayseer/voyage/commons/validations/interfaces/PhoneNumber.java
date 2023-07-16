package ru.krayseer.voyage.commons.validations.interfaces;


import ru.krayseer.voyage.commons.constants.RegExp;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@NotBlank(message = "phoneNumber должен быть заполнен")
@Pattern(regexp = RegExp.PHONE_NUMBER, message = "некорректный телефонный номер")
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface PhoneNumber {

    String message() default "неверно введён телефонный номер";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
