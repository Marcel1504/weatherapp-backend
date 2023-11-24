package me.marcelberger.weatherapp.assistant.data.weather;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherAggregationEnum;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherRecordEnum;

@Data
@Builder
public class WeatherRecordFunctionCallData {
    private String station;
    private WeatherRecordEnum type;
    private WeatherAggregationEnum aggregation;
}
