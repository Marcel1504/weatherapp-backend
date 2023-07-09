package me.marcelberger.weatherapp.api.service.sort.impl;

import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.service.sort.SortService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WeatherSortServiceImpl implements SortService<WeatherSortEnum> {
    @Override
    public Sort forHour(WeatherSortEnum sortEnum) {
        return switch (sortEnum) {
            case LATEST -> Sort.by(Sort.Order.desc("hour"), Sort.Order.desc("day"));
            case OLDEST -> Sort.by(Sort.Order.asc("hour"), Sort.Order.asc("day"));
            default -> forDefaultSort(sortEnum);
        };
    }

    @Override
    public Sort forDay(WeatherSortEnum sortEnum) {
        return switch (sortEnum) {
            case LATEST -> Sort.by(Sort.Order.desc("day"));
            case OLDEST -> Sort.by(Sort.Order.asc("day"));
            default -> forDefaultSort(sortEnum);
        };
    }

    @Override
    public Sort forMonth(WeatherSortEnum sortEnum) {
        return switch (sortEnum) {
            case LATEST -> Sort.by(Sort.Order.desc("year"), Sort.Order.desc("month"));
            case OLDEST -> Sort.by(Sort.Order.asc("year"), Sort.Order.asc("month"));
            default -> forDefaultSort(sortEnum);
        };
    }

    @Override
    public Sort forYear(WeatherSortEnum sortEnum) {
        return switch (sortEnum) {
            case LATEST -> Sort.by(Sort.Order.desc("year"));
            case OLDEST -> Sort.by(Sort.Order.asc("year"));
            default -> forDefaultSort(sortEnum);
        };
    }

    private Sort forDefaultSort(WeatherSortEnum sortEnum) {
        return switch (sortEnum) {
            case HIGHEST_TEMPERATURE -> Sort.by(
                    Sort.Order.desc("temperatureMax"));
            case LOWEST_TEMPERATURE -> Sort.by(
                    Sort.Order.asc("temperatureMin"));
            case MOST_RAIN -> Sort.by(
                    Sort.Order.desc("rainTotal"));
            case STRONGEST_WIND -> Sort.by(
                    Sort.Order.desc("windMax"));
            case HIGHEST_PRESSURE -> Sort.by(
                    Sort.Order.desc("pressureMax"));
            case LOWEST_PRESSURE -> Sort.by(
                    Sort.Order.asc("pressureMin"));
            case HIGHEST_SOLAR_RADIATION -> Sort.by(
                    Sort.Order.desc("solarRadiationMax"));
            case LOWEST_SOLAR_RADIATION -> Sort.by(
                    Sort.Order.asc("solarRadiationMin"));
            default -> Sort.unsorted();
        };
    }
}
