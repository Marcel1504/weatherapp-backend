package me.marcelberger.weatherapp.assistant.enumeration.openai;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenAIFinishReasonEnum {
    @JsonProperty("function_call")
    FUNCTION_CALL(OpenAIFinishReasonEnum.FUNCTION_CALL_VALUE),

    @JsonEnumDefaultValue
    @JsonProperty("stop")
    STOP(OpenAIFinishReasonEnum.STOP_VALUE);

    public static final String FUNCTION_CALL_VALUE = "FUNCTION_CALL";
    public static final String STOP_VALUE = "STOP";

    private final String value;
}
