package me.marcelberger.weatherapp.assistant.data.weather.time;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherAggregationEnum;

@Data
@Builder
public class WeatherTimeData {
    Double temperature;
    Double windMax;
    Double humidity;
    Double rainTotal;
    String date;
    String station;
    WeatherAggregationEnum type;
}
