package me.marcelberger.weatherapp.api.validator.year;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NullableYearStringValidator.class)
public @interface NullableYearString {
    String message() default "{validator.year.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
