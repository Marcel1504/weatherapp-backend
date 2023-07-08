package me.marcelberger.weatherapp.api.validator.day;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NullableDayStringValidator.class)
public @interface NullableDayString {
    String message() default "{validator.day.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}