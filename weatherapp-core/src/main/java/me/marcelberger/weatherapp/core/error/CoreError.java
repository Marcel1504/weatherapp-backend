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
        // Validation of request failed
        CORE00001("CORE00001"),
        // Authentication of request failed
        CORE00002("CORE00002"),
        // Too many requests
        CORE00003("CORE00003"),
        // Station not found
        CORE00200("CORE00200"),
        // Station media not found
        CORE00300("CORE00300"),
        // Weather data not found
        CORE00400("CORE00400"),
        // Soil data not found
        CORE00500("CORE00500");
        private final String value;
    }
}
