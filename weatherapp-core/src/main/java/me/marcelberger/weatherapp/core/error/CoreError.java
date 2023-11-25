package me.marcelberger.weatherapp.core.error;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class CoreError extends RuntimeException {
    private final Code code;
    private final String message;

    public CoreError(Code code, String message, String... messageProperties) {
        this.code = code;
        this.message = String.format(message, (Object[]) messageProperties);
    }

    @Getter
    @AllArgsConstructor
    public enum Code {
        CORE00001("Validation of request failed"),
        CORE00002("Authentication of request failed"),
        CORE00003("Too many requests"),
        CORE00200("Station not found"),
        CORE00300("Station media not found"),
        CORE00400("Weather data not found"),
        CORE00500("Soil data not found");
        private final String value;
    }
}
