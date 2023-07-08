package me.marcelberger.weatherapp.api.data.soil;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SoilData {
    private Double temperature50cm;
    private Double temperature100cm;
    private Double temperature200cm;
    private String temperature50cmColor;
    private String temperature100cmColor;
    private String temperature200cmColor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
