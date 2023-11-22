package me.marcelberger.weatherapp.assistant.data.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIRoleEnum;

@Data
@Builder
public class OpenAIMessageData {
    private OpenAIRoleEnum role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OpenAIFunctionEnum name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("function_call")
    private OpenAIFunctionCallData functionCall;
}
