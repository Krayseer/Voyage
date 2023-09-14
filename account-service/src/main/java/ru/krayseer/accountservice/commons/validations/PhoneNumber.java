package ru.krayseer.accountservice.commons.validations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import ru.krayseer.accountservice.commons.constants.RegExp;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@NotBlank(message = "phoneNumber must be filled in")
@Pattern(regexp = RegExp.PHONE_NUMBER, message = "invalid phone number")
public @interface PhoneNumber {

    String message() default "phone number entered incorrectly";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
