package me.marcelberger.weatherapp.receiver.data.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SOPRequestData {
    private Double temperature1;
    private Double temperature2;
    private Double temperature3;
    private LocalDateTime timestamp;
}
