package me.marcelberger.weatherapp.assistant.enumeration.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherAggregationEnum {
    @JsonProperty("day")
    DAY(WeatherAggregationEnum.DAY_VALUE),

    @JsonProperty("month")
    MONTH(WeatherAggregationEnum.MONTH_VALUE),

    @JsonProperty("year")
    YEAR(WeatherAggregationEnum.YEAR_VALUE);

    public static final String DAY_VALUE = "DAY";
    public static final String MONTH_VALUE = "MONTH";
    public static final String YEAR_VALUE = "YEAR";

    private final String value;
}
