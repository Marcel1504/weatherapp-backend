package me.marcelberger.weatherapp.core.repository.soil;

public final class SoilRepositoryQueries {
    public static final String SELECT_FROM = "SELECT s.id, s.timestamp, " +
            "s.temperature50cm, s.temperature100cm, s.temperature200cm, s.station_id " +
            "FROM so_soil s ";
}
