package me.marcelberger.weatherapp.api.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherSortEnum {
    LATEST(WeatherSortEnum.LATEST_VALUE),
    OLDEST(WeatherSortEnum.OLDEST_VALUE),
    HIGHEST_TEMPERATURE(WeatherSortEnum.HIGHEST_TEMPERATURE_VALUE),
    LOWEST_TEMPERATURE(WeatherSortEnum.LOWEST_TEMPERATURE_VALUE),
    HIGHEST_HUMIDITY(WeatherSortEnum.HIGHEST_HUMIDITY_VALUE),
    LOWEST_HUMIDITY(WeatherSortEnum.LOWEST_HUMIDITY_VALUE),
    HIGHEST_SOLAR_RADIATION(WeatherSortEnum.HIGHEST_SOLAR_RADIATION_VALUE),
    LOWEST_SOLAR_RADIATION(WeatherSortEnum.LOWEST_SOLAR_RADIATION_VALUE),
    HIGHEST_PRESSURE(WeatherSortEnum.HIGHEST_PRESSURE_VALUE),
    LOWEST_PRESSURE(WeatherSortEnum.LOWEST_PRESSURE_VALUE),
    MOST_RAIN(WeatherSortEnum.MOST_RAIN_VALUE),
    STRONGEST_WIND(WeatherSortEnum.STRONGEST_WIND_VALUE);

    public static final String LATEST_VALUE = "LATEST";
    public static final String OLDEST_VALUE = "OLDEST";
    public static final String HIGHEST_TEMPERATURE_VALUE = "HIGHEST_TEMPERATURE";
    public static final String LOWEST_TEMPERATURE_VALUE = "LOWEST_TEMPERATURE";
    public static final String HIGHEST_HUMIDITY_VALUE = "HIGHEST_HUMIDITY";
    public static final String LOWEST_HUMIDITY_VALUE = "LOWEST_HUMIDITY";
    public static final String HIGHEST_SOLAR_RADIATION_VALUE = "HIGHEST_SOLAR_RADIATION";
    public static final String LOWEST_SOLAR_RADIATION_VALUE = "LOWEST_SOLAR_RADIATION";
    public static final String HIGHEST_PRESSURE_VALUE = "HIGHEST_PRESSURE";
    public static final String LOWEST_PRESSURE_VALUE = "LOWEST_PRESSURE";
    public static final String MOST_RAIN_VALUE = "MOST_RAIN";
    public static final String STRONGEST_WIND_VALUE = "STRONGEST_WIND";

    private final String value;
}
