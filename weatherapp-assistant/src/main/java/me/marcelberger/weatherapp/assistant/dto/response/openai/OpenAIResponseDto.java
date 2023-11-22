package me.marcelberger.weatherapp.assistant.dto.response.openai;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIChoiceData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIUsageData;

import java.util.List;

@Data
@Builder
public class OpenAIResponseDto {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<OpenAIChoiceData> choices;
    private OpenAIUsageData usage;
}
