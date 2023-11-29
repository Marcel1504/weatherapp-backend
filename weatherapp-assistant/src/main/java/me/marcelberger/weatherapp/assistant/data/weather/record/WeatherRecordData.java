package me.marcelberger.weatherapp.assistant.data.weather.record;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherAggregationEnum;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherRecordEnum;

@Data
@Builder
public class WeatherRecordData {
    Double temperatureMax;
    Double temperatureMin;
    Double windMax;
    Double rainTotal;
    String date;
    String station;
    WeatherRecordEnum type;
    WeatherAggregationEnum aggregation;
}
