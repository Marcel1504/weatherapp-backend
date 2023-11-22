package me.marcelberger.weatherapp.assistant.enumeration.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherRecordEnum {
    @JsonProperty("hottest")
    HOTTEST(WeatherRecordEnum.HOTTEST_VALUE),

    @JsonProperty("coldest")
    COLDEST(WeatherRecordEnum.COLDEST_VALUE),

    @JsonProperty("mostRain")
    MOST_RAIN(WeatherRecordEnum.MOST_RAIN_VALUE);

    public static final String HOTTEST_VALUE = "HOTTEST";
    public static final String COLDEST_VALUE = "COLDEST";
    public static final String MOST_RAIN_VALUE = "MOST_RAIN";

    private final String value;
}
