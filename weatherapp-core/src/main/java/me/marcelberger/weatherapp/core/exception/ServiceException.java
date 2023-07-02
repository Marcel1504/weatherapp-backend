package me.marcelberger.weatherapp.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private final String message;

    public ServiceException(String message, Object... values) {
        this.message = String.format(message, values);
    }
}
