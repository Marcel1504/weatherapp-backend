package me.marcelberger.weatherapp.api.dto.response.station;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;

import java.time.LocalDateTime;

@Data
@Builder
public class StationResponseDto {
    String code;
    String name;
    StationTypeEnum type;
    Double latitude;
    Double longitude;
    Integer altitude;
    StationMediaResponseDto latestStationMedia;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastActivity;
}
