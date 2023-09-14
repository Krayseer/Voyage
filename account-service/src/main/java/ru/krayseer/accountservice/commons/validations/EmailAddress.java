package ru.krayseer.accountservice.commons.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Email
@NotBlank
@Documented
@Size(max = 100)
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EmailAddress {

    String message() default "incorrect email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
