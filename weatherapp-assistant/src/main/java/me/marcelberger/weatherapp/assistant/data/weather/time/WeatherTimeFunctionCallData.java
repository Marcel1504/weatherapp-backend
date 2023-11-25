package me.marcelberger.weatherapp.assistant.data.weather.time;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherAggregationEnum;

import java.time.LocalDate;

@Data
@Builder
public class WeatherTimeFunctionCallData {
    private String station;
    private LocalDate date;
    private WeatherAggregationEnum aggregation;
}
