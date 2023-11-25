package me.marcelberger.weatherapp.assistant.service.weather.time;

import me.marcelberger.weatherapp.assistant.data.weather.time.WeatherTimeData;
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
public class WeatherTimeServiceImpl implements WeatherTimeService {

    @Autowired
    private DaySummaryFacade<WeatherDaySummaryEntity, WeatherDaySummaryData, WeatherSortEnum> daySummaryFacade;

    @Autowired
    private MonthSummaryFacade<WeatherMonthSummaryEntity, WeatherMonthSummaryData, WeatherSortEnum> monthSummaryFacade;

    @Autowired
    private YearSummaryFacade<WeatherYearSummaryEntity, WeatherYearSummaryData, WeatherSortEnum> yearSummaryFacade;

    @Autowired
    private Mapper<WeatherSummaryData, WeatherTimeData> weatherSummaryDataWeatherTimeDataMapper;

    @Override
    public WeatherTimeData getWeatherDayOrNull(String date, StationData station) {
        WeatherDaySummaryData data = daySummaryFacade.getDayForStationOrNull(station.getCode(), date);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }

    @Override
    public WeatherTimeData getWeatherMonthOrNull(String year, String month, StationData station) {
        WeatherMonthSummaryData data = monthSummaryFacade.getMonthForStationOrNull(station.getCode(), month, year);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }

    @Override
    public WeatherTimeData getWeatherYearOrNull(String year, StationData station) {
        WeatherYearSummaryData data = yearSummaryFacade.getYearForStationOrNull(station.getCode(), year);
        return data != null ? weatherSummaryDataWeatherTimeDataMapper.map(data) : null;
    }
}
