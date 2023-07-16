package ru.krayseer.voyage.commons.validations;

import ru.krayseer.voyage.commons.validations.interfaces.LicensePlate;
import ru.krayseer.voyage.commons.constants.RegExp;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LicensePlateValidator implements ConstraintValidator<LicensePlate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.matches(RegExp.CAR_NUMBER);
    }

}
