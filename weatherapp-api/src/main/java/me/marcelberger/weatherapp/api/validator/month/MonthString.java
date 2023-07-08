package me.marcelberger.weatherapp.api.validator.month;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MonthStringValidator.class)
public @interface MonthString {
    String message() default "{validator.month.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
