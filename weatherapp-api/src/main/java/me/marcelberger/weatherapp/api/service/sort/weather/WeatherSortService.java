package me.marcelberger.weatherapp.api.service.sort.weather;

import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import org.springframework.data.domain.Sort;

public interface WeatherSortService {
    Sort forHour(WeatherSortEnum sortEnum);

    Sort forDay(WeatherSortEnum sortEnum);

    Sort forMonth(WeatherSortEnum sortEnum);

    Sort forYear(WeatherSortEnum sortEnum);
}
