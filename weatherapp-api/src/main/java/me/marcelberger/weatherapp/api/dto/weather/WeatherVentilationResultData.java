package me.marcelberger.weatherapp.api.dto.weather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherVentilationResultData {
    private Double absoluteHumidityOut;
    private Double absoluteHumidityIn;
    private String indicator;
    private String indicatorTitle;
    private String indicatorDescription;
}
