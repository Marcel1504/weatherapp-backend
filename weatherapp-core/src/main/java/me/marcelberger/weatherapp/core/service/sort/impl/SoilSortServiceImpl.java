package me.marcelberger.weatherapp.core.service.sort.impl;

import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.service.sort.SortService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SoilSortServiceImpl implements SortService<SoilSortEnum> {

    @Override
    public Sort forHour(SoilSortEnum sortEnum) {
        return switch (sortEnum) {
            case LATEST -> Sort.by(Sort.Order.desc("hour"), Sort.Order.desc("day"));
            case OLDEST -> Sort.by(Sort.Order.asc("hour"), Sort.Order.asc("day"));
            default -> forDefaultSort(sortEnum);
        };
    }

    @Override
    public Sort forDay(SoilSortEnum sortEnum) {
        return switch (sortEnum) {
            case LATEST -> Sort.by(Sort.Order.desc("day"));
            case OLDEST -> Sort.by(Sort.Order.asc("day"));
            default -> forDefaultSort(sortEnum);
        };
    }

    @Override
    public Sort forMonth(SoilSortEnum sortEnum) {
        return switch (sortEnum) {
            case LATEST -> Sort.by(Sort.Order.desc("year"), Sort.Order.desc("month"));
            case OLDEST -> Sort.by(Sort.Order.asc("year"), Sort.Order.asc("month"));
            default -> forDefaultSort(sortEnum);
        };
    }

    @Override
    public Sort forYear(SoilSortEnum sortEnum) {
        return switch (sortEnum) {
            case LATEST -> Sort.by(Sort.Order.desc("year"));
            case OLDEST -> Sort.by(Sort.Order.asc("year"));
            default -> forDefaultSort(sortEnum);
        };
    }

    private Sort forDefaultSort(SoilSortEnum sortEnum) {
        return switch (sortEnum) {
            case HIGHEST_TEMPERATURE50CM -> Sort.by(
                    Sort.Order.desc("temperature50cmMax"));
            case LOWEST_TEMPERATURE50CM -> Sort.by(
                    Sort.Order.asc("temperature50cmMin"));
            case HIGHEST_TEMPERATURE100CM -> Sort.by(
                    Sort.Order.desc("temperature100cmMax"));
            case LOWEST_TEMPERATURE100CM -> Sort.by(
                    Sort.Order.asc("temperature100cmMin"));
            case HIGHEST_TEMPERATURE200CM -> Sort.by(
                    Sort.Order.desc("temperature200cmMax"));
            case LOWEST_TEMPERATURE200CM -> Sort.by(
                    Sort.Order.asc("temperature200cmMin"));
            default -> Sort.unsorted();
        };
    }
}
