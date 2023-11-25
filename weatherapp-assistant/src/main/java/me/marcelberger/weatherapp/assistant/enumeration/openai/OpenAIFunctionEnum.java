package me.marcelberger.weatherapp.assistant.enumeration.openai;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenAIFunctionEnum {
    @JsonProperty("weather_time")
    WEATHER_TIME(OpenAIFunctionEnum.WEATHER_TIME_VALUE),

    @JsonProperty("weather_record")
    WEATHER_RECORD(OpenAIFunctionEnum.WEATHER_RECORD_VALUE),

    @JsonProperty("weather_media")
    WEATHER_MEDIA(OpenAIFunctionEnum.WEATHER_MEDIA_VALUE),

    /**
     * Sometimes OpenAI hallucinates "python" as function, which will be caught with this
     */
    @JsonEnumDefaultValue
    UNKNOWN(OpenAIFunctionEnum.UNKNOWN_VALUE);

    public static final String WEATHER_TIME_VALUE = "WEATHER_TIME";
    public static final String WEATHER_RECORD_VALUE = "WEATHER_RECORD";
    public static final String WEATHER_MEDIA_VALUE = "WEATHER_MEDIA";
    public static final String UNKNOWN_VALUE = "UNKNOWN";

    private final String value;
}
