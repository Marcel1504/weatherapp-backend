package me.marcelberger.weatherapp.core.data.summary;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.enumeration.weather.WeatherWindDirectionEnum;

@Data
@SuperBuilder
public abstract class WeatherSummaryData {
    private Integer amount;
    private Double temperatureAvg;
    private Double temperatureMax;
    private Double temperatureMin;
    private Double humidityAvg;
    private Integer humidityMax;
    private Integer humidityMin;
    private Double rainTotal;
    private Double rainRateMax;
    private Double windMax;
    private WeatherWindDirectionEnum windDirectionCluster;
    private Double pressureAvg;
    private Double pressureMax;
    private Double pressureMin;
    private Double solarRadiationAvg;
    private Double solarRadiationMax;
    private Double solarRadiationMin;
    private String stationName;
}
