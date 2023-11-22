package me.marcelberger.weatherapp.assistant.enumeration.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenAIRoleEnum {
    @JsonProperty("user")
    USER(OpenAIRoleEnum.USER_VALUE),

    @JsonProperty("assistant")
    ASSISTANT(OpenAIRoleEnum.ASSISTANT_VALUE),

    @JsonProperty("system")
    SYSTEM(OpenAIRoleEnum.SYSTEM_VALUE),

    @JsonProperty("function")
    FUNCTION(OpenAIRoleEnum.FUNCTION_VALUE);

    public static final String ASSISTANT_VALUE = "ASSISTANT";
    public static final String USER_VALUE = "USER";
    public static final String FUNCTION_VALUE = "FUNCTION";
    public static final String SYSTEM_VALUE = "SYSTEM";

    private final String value;
}
