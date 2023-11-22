package me.marcelberger.weatherapp.assistant.service.weather.time;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.month.WeatherMonthSummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.day.DaySummaryRepository;
import me.marcelberger.weatherapp.core.repository.summary.month.MonthSummaryRepository;
import me.marcelberger.weatherapp.core.repository.summary.year.YearSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherTimeServiceImpl implements WeatherTimeService {

    @Autowired
    private DaySummaryRepository<WeatherDaySummaryEntity> daySummaryRepository;

    @Autowired
    private MonthSummaryRepository<WeatherMonthSummaryEntity> monthSummaryRepository;

    @Autowired
    private YearSummaryRepository<WeatherYearSummaryEntity> yearSummaryRepository;

    @Override
    public WeatherDaySummaryEntity getWeatherDay(String date, StationEntity station) {
        return daySummaryRepository.findByStationAndDay(station, date);
    }

    @Override
    public WeatherMonthSummaryEntity getWeatherMonth(String year, String month, StationEntity station) {
        return monthSummaryRepository.findByStationAndMonthAndYear(station, month, year);
    }

    @Override
    public WeatherYearSummaryEntity getWeatherYear(String year, StationEntity station) {
        return yearSummaryRepository.findByStationAndYear(station, year);
    }
}
