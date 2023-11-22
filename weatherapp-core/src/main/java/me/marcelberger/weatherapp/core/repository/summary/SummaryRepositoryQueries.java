package me.marcelberger.weatherapp.core.repository.summary;

public final class SummaryRepositoryQueries {
    public static final String SELECT_FROM_SOIL = "SELECT s.id, s.timestamp, " +
            "s.temperature50cm, s.temperature100cm, s.temperature200cm, s.station_id " +
            "FROM so_soil s ";

    public static final String SELECT_FROM_WEATHER = "SELECT w.id, w.temperature, w.humidity, w.rain_delta, " +
            "w.rain_rate, w.wind, w.wind_direction, w.pressure, w.solar_radiation, w.timestamp, w.station_id " +
            "FROM we_weather w ";
}
