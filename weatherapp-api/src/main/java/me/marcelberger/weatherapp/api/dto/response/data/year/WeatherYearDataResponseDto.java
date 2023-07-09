package me.marcelberger.weatherapp.api.dto.response.data.year;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.api.dto.response.data.DataResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WeatherYearDataResponseDto implements DataResponseDto {
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
    private String year;
}
