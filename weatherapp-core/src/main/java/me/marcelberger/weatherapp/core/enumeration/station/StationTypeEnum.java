package me.marcelberger.weatherapp.core.enumeration.station;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StationTypeEnum {
    WEATHER("WEATHER"),
    SOIL("SOIL");
    private final String value;
}
