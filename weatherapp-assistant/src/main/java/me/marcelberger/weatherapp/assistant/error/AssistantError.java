package me.marcelberger.weatherapp.assistant.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssistantError extends RuntimeException {
    private final AssistantError.Code code;
    private final String message;

    public AssistantError(AssistantError.Code code, String message, String... messageProperties) {
        this.code = code;
        this.message = String.format(message, (Object[]) messageProperties);
    }

    @Getter
    @AllArgsConstructor
    public enum Code {
        // OpenAI request timeout
        ASSISTANT00001("ASSISTANT00001"),
        // Error processing message data
        ASSISTANT00002("ASSISTANT00002"),
        // OpenAI connection failure
        ASSISTANT00003("ASSISTANT00003"),
        // Too many requests
        ASSISTANT00004("ASSISTANT00004");
        private final String value;
    }
}
