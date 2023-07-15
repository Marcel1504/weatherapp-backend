package me.marcelberger.weatherapp.api.dto.station;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class StationData {
    String code;
    String name;
    StationTypeEnum type;
    Double latitude;
    Double longitude;
    Integer altitude;
    List<StationMediaData> stationImageUrls;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastActivity;
}
