package me.marcelberger.weatherapp.api.dto.response.data.single;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WeatherSingleDataResponseDto {
    private Double temperature;
    private Integer humidity;
    private Double rainSinceLast;
    private Double rainRate;
    private Double wind;
    private Integer windDirection;
    private Double pressure;
    private Double solarRadiation;
    private Long secondsSinceLast;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
