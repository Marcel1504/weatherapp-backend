package me.marcelberger.weatherapp.api.dto.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class WeatherSummaryDataResponseDto {
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
    private String windDirectionCluster;
    private Double pressureAvg;
    private Double pressureMax;
    private Double pressureMin;
    private Double solarRadiationAvg;
    private Double solarRadiationMax;
    private Double solarRadiationMin;
}
