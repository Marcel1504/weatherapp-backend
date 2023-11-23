package me.marcelberger.weatherapp.assistant.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssistantException extends RuntimeException {
    String message;
}
