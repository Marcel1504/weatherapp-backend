package me.marcelberger.weatherapp.core.data.station;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.core.enumeration.station.StationTypeEnum;

import java.time.LocalDateTime;

@Data
@Builder
public class StationData {
    String code;
    String name;
    StationTypeEnum type;
    Double latitude;
    Double longitude;
    Integer altitude;
    StationMediaData latestStationMedia;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastActivity;
}
