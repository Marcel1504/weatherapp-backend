package me.marcelberger.weatherapp.api.validator.day;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static jakarta.validation.constraintvalidation.ValidationTarget.ANNOTATED_ELEMENT;

@SupportedValidationTarget({ANNOTATED_ELEMENT})
public class DayStringValidator implements ConstraintValidator<DayString, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (Exception e) {
            ((ConstraintValidatorContextImpl) context).addMessageParameter("value", value);
            return false;
        }
    }
}
