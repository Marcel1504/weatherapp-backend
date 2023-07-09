package me.marcelberger.weatherapp.api.service.sort;

import org.springframework.data.domain.Sort;

public interface SortService<SORT> {
    Sort forHour(SORT sortEnum);

    Sort forDay(SORT sortEnum);

    Sort forMonth(SORT sortEnum);

    Sort forYear(SORT sortEnum);
}
