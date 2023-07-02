package me.marcelberger.weatherapp.receiver.data.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WEPRequestData {
    private Double temperature;
    private Integer humidity;
    private Double rainfall;
    private Double rainRate;
    private Double wind;
    private Integer windDirection;
    private Double pressure;
    private LocalDateTime timestamp;
}
