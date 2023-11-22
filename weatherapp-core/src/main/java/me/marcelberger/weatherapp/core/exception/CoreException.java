package me.marcelberger.weatherapp.core.exception;

import lombok.Getter;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;

import java.util.List;

@Getter
public class CoreException extends RuntimeException {
    private final ErrorCodeEnum code;
    private final List<String> properties;
    private final String message;

    public CoreException(ErrorCodeEnum code, String... properties) {
        this.code = code;
        this.properties = List.of(properties);
        this.message = String.format(code.getValue(), (Object[]) properties);
    }
}
