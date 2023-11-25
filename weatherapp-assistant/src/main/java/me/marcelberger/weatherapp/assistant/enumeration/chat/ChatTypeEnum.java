package me.marcelberger.weatherapp.assistant.enumeration.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatTypeEnum {
    TEXT(ChatTypeEnum.TEXT_VALUE),
    ERROR(ChatTypeEnum.ERROR_VALUE),
    INTERNAL(ChatTypeEnum.INTERNAL_VALUE),
    WEATHER_TIME(ChatTypeEnum.WEATHER_TIME_VALUE),
    WEATHER_RECORD(ChatTypeEnum.WEATHER_RECORD_VALUE),
    WEATHER_MEDIA(ChatTypeEnum.IMAGE_VALUE);

    public static final String TEXT_VALUE = "TEXT";
    public static final String ERROR_VALUE = "ERROR";
    public static final String INTERNAL_VALUE = "INTERNAL";
    public static final String WEATHER_TIME_VALUE = "WEATHER_TIME";
    public static final String WEATHER_RECORD_VALUE = "WEATHER_RECORD";
    public static final String IMAGE_VALUE = "WEATHER_MEDIA";

    private final String value;
}
