package me.marcelberger.weatherapp.assistant.data.weather;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherAggregationEnum;

import java.time.LocalDate;

@Data
@Builder
public class WeatherTimeFunctionData {
    private String station;
    private LocalDate date;
    private WeatherAggregationEnum aggregation;
}
