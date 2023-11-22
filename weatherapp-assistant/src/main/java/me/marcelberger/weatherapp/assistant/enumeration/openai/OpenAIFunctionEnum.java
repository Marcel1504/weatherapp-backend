package me.marcelberger.weatherapp.assistant.enumeration.openai;

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
    WEATHER_MEDIA(OpenAIFunctionEnum.WEATHER_MEDIA_VALUE);

    public static final String WEATHER_TIME_VALUE = "WEATHER_TIME";
    public static final String WEATHER_RECORD_VALUE = "WEATHER_RECORD";
    public static final String WEATHER_MEDIA_VALUE = "WEATHER_MEDIA";

    private final String value;
}
