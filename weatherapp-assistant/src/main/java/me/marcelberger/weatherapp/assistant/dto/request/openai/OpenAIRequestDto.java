package me.marcelberger.weatherapp.assistant.dto.request.openai;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;

import java.util.List;

@Data
@Builder
public class OpenAIRequestDto {
    private String model;
    private List<OpenAIMessageData> messages;
    private List<OpenAIFunctionData> functions;
}
