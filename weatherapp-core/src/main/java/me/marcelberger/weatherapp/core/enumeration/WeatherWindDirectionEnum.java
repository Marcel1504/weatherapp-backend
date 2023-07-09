package me.marcelberger.weatherapp.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherWindDirectionEnum {
    N("N"),
    NE("NE"),
    E("E"),
    SE("SE"),
    S("S"),
    SW("SW"),
    W("W"),
    NW("NW");
    private final String value;
}
