package me.marcelberger.weatherapp.assistant.data.openai;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;

@Data
@Builder
public class OpenAIFunctionCallData {
    OpenAIFunctionEnum name;
    String arguments;
}
