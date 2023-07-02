package me.marcelberger.weatherapp.core.repository;

public final class WeatherRepositoryQueries {
    public static final String SELECT_FROM = "SELECT w.id, w.temperature, w.humidity, w.rain_delta, " +
            "w.rain_rate, w.wind, w.wind_direction, w.pressure, w.solar_radiation, w.timestamp, w.station_id " +
            "FROM we_weather w ";
}
