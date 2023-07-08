package me.marcelberger.weatherapp.api.data.weather;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WeatherData {
    private Double temperature;
    private String temperatureColor;
    private Integer humidity;
    private Double rainSinceLast;
    private Double rainRate;
    private Double wind;
    private Integer windDirection;
    private String windDirectionCluster;
    private Double pressure;
    private Double solarRadiation;
    private Long secondsSinceLast;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
