package me.marcelberger.weatherapp.assistant.data.openai;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;

@Data
@Builder
public class OpenAIFunctionData {
    private OpenAIFunctionEnum name;
    private String description;
    private Object parameters;
}