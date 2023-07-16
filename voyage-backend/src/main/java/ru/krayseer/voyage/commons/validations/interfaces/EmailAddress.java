package ru.krayseer.voyage.commons.validations.interfaces;

import jakarta.validation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@NotBlank
@Email
@Size(max = 100)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface EmailAddress {

    String message() default "некорректный email адрес";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
