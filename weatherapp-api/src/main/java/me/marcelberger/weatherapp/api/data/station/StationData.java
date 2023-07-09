package me.marcelberger.weatherapp.api.data.station;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;

@Data
@Builder
public class StationData {
    String code;
    String name;
    StationTypeEnum type;
    Double latitude;
    Double longitude;
    Integer altitude;
}
