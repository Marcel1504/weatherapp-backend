package me.marcelberger.weatherapp.core.enumeration.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    CODE00001("Validation failed"),
    CODE00020("Station(code=%s) not found"),
    CODE00021("StationMedia(name=%s) for Station(code=%s) not found"),
    CODE00022("Station(key=%s) not found"),
    CODE00023("Station is null"),
    CODE00100("WeatherSummary(year=%s) for Station(code=%s) not found"),
    CODE00101("WeatherSummary(single) for Station(code=%s) not found"),
    CODE00102("WeatherSummary(year=%s,month=%s) for Station(code=%s) not found"),
    CODE00103("WeatherSummary(day=%s) for Station(code=%s) not found"),
    CODE00200("SoilSummary(year=%s) for Station(code=%s) not found"),
    CODE00201("SoilSummary(single) for Station(code=%s) not found"),
    CODE00202("SoilSummary(year=%s,month=%s) for Station(code=%s) not found"),
    CODE00203("SoilSummary(day=%s) for Station(code=%s) not found");
    private final String value;
}
