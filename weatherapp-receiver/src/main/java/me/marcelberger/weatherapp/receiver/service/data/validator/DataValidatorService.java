package me.marcelberger.weatherapp.receiver.service.data.validator;

public interface DataValidatorService<T> {
    boolean isValid(T element);
}
