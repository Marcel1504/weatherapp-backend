package me.marcelberger.weatherapp.core.data.summary.single;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WeatherSingleSummaryData {
    private Double temperature;
    private Integer humidity;
    private Double rainRate;
    private Double wind;
    private Integer windDirection;
    private Double pressure;
    private Double solarRadiation;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastRain;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
