package me.marcelberger.weatherapp.api.validator.year;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import static jakarta.validation.constraintvalidation.ValidationTarget.ANNOTATED_ELEMENT;

@SupportedValidationTarget({ANNOTATED_ELEMENT})
public class NullableYearStringValidator implements ConstraintValidator<NullableYearString, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value.matches("^[12][0-9]{3}$")) {
            return true;
        } else {
            ((ConstraintValidatorContextImpl) context).addMessageParameter("value", value);
            return false;
        }
    }
}
