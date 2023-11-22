package me.marcelberger.weatherapp.assistant.data.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFinishReasonEnum;

@Data
@Builder
public class OpenAIChoiceData {
    private Long index;
    private OpenAIMessageData message;

    @JsonProperty("finish_reason")
    private OpenAIFinishReasonEnum finishReason;
}
