package me.marcelberger.weatherapp.api.data.weather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherSummaryData {
    private Integer amount;
    private Double temperatureAvg;
    private Double temperatureMax;
    private Double temperatureMin;
    private String temperatureAvgColor;
    private String temperatureMaxColor;
    private String temperatureMinColor;
    private Integer humidityAvg;
    private Integer humidityMax;
    private Integer humidityMin;
    private Double rainTotal;
    private Double rainRateMax;
    private Double windMax;
    private Double pressureAvg;
    private Double pressureMax;
    private Double pressureMin;
    private Double solarRadiationAvg;
    private Double solarRadiationMax;
    private Double solarRadiationMin;
    private String day;
    private String hour;
    private String month;
    private String year;
}
