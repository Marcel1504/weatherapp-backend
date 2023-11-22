package me.marcelberger.weatherapp.assistant.data.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenAIUsageData {
    @JsonProperty("prompt_token")
    private String promptTokens;

    @JsonProperty("completion_tokens")
    private String completionTokens;

    @JsonProperty("total_tokens")
    private String totalToken;
}
