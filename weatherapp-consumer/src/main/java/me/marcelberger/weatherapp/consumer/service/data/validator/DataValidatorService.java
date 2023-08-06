package me.marcelberger.weatherapp.consumer.service.data.validator;

public interface DataValidatorService<T> {
    boolean isValid(T element);
}
