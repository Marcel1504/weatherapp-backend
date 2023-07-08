package me.marcelberger.weatherapp.api.validator.month;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import static jakarta.validation.constraintvalidation.ValidationTarget.ANNOTATED_ELEMENT;

@SupportedValidationTarget({ANNOTATED_ELEMENT})
public class MonthStringValidator implements ConstraintValidator<MonthString, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.matches("^(0[1-9])|(1[012])$")) {
            return true;
        } else {
            ((ConstraintValidatorContextImpl) context).addMessageParameter("value", value);
            return false;
        }
    }
}
