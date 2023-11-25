package me.marcelberger.weatherapp.assistant.service.weather.record;

import me.marcelberger.weatherapp.assistant.data.weather.record.WeatherRecordData;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherRecordEnum;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.data.summary.WeatherSummaryData;
import me.marcelberger.weatherapp.core.data.summary.day.WeatherDaySummaryData;
import me.marcelberger.weatherapp.core.data.summary.month.WeatherMonthSummaryData;
import me.marcelberger.weatherapp.core.data.summary.year.WeatherYearSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.month.WeatherMonthSummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.day.DaySummaryFacade;
import me.marcelberger.weatherapp.core.facade.summary.month.MonthSummaryFacade;
import me.marcelberger.weatherapp.core.facade.summary.year.YearSummaryFacade;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherRecordServiceImpl implements WeatherRecordService {

    @Autowired
    private DaySummaryFacade<WeatherDaySummaryEntity, WeatherDaySummaryData, WeatherSortEnum> daySummaryFacade;

    @Autowired
    private MonthSummaryFacade<WeatherMonthSummaryEntity, WeatherMonthSummaryData, WeatherSortEnum> monthSummaryFacade;

    @Autowired
    private YearSummaryFacade<WeatherYearSummaryEntity, WeatherYearSummaryData, WeatherSortEnum> yearSummaryFacade;

    @Autowired
    private Mapper<WeatherSummaryData, WeatherRecordData> weatherSummaryDataWeatherTimeDataMapper;

    @Override
    public WeatherRecordData getWeatherDayOrNull(WeatherRecordEnum type, StationData station) {
        WeatherDaySummaryData data = daySummaryFacade.getAllDaysForStation(
                        station.getCode(), 0, 1, null, null, determineWeatherSort(type))
                .getList()
                .stream()
                .findFirst()
                .orElse(null);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }

    @Override
    public WeatherRecordData getWeatherDayForAllStationsOrNull(WeatherRecordEnum type) {
        WeatherDaySummaryData data = daySummaryFacade.getAllDays(
                        0, 1, determineWeatherSort(type))
                .getList()
                .stream()
                .findFirst()
                .orElse(null);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }

    @Override
    public WeatherRecordData getWeatherMonthOrNull(WeatherRecordEnum type, StationData station) {
        WeatherMonthSummaryData data = monthSummaryFacade.getAllMonthsForStation(
                        station.getCode(), 0, 1, determineWeatherSort(type))
                .getList()
                .stream()
                .findFirst()
                .orElse(null);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }

    @Override
    public WeatherRecordData getWeatherMonthForAllStationsOrNull(WeatherRecordEnum type) {
        WeatherMonthSummaryData data = monthSummaryFacade.getAllMonths(
                        0, 1, determineWeatherSort(type))
                .getList()
                .stream()
                .findFirst()
                .orElse(null);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }

    @Override
    public WeatherRecordData getWeatherYearOrNull(WeatherRecordEnum type, StationData station) {
        WeatherYearSummaryData data = yearSummaryFacade.getAllYearsForStation(
                        station.getCode(), 0, 1, determineWeatherSort(type))
                .getList()
                .stream()
                .findFirst()
                .orElse(null);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }

    @Override
    public WeatherRecordData getWeatherYearForAllStationsOrNull(WeatherRecordEnum type) {
        WeatherYearSummaryData data = yearSummaryFacade.getAllYears(
                        0, 1, determineWeatherSort(type))
                .getList()
                .stream()
                .findFirst()
                .orElse(null);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }

    private WeatherSortEnum determineWeatherSort(WeatherRecordEnum type) {
        return switch (type) {
            case HOTTEST -> WeatherSortEnum.HIGHEST_TEMPERATURE;
            case COLDEST -> WeatherSortEnum.LOWEST_TEMPERATURE;
            case MOST_RAIN -> WeatherSortEnum.MOST_RAIN;
            case STRONGEST_WIND -> WeatherSortEnum.STRONGEST_WIND;
        };
    }
}
