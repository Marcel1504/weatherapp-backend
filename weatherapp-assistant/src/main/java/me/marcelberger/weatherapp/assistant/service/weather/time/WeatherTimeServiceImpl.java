package me.marcelberger.weatherapp.assistant.service.weather.time;

import me.marcelberger.weatherapp.assistant.data.weather.WeatherTimeData;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherAggregationEnum;
import me.marcelberger.weatherapp.core.data.station.StationData;
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

    @Override
    public WeatherTimeData getWeatherDayOrNull(String date, StationData station) {
        WeatherDaySummaryData data = daySummaryFacade.getDayForStationOrNull(station.getCode(), date);
        return data != null
                ? WeatherTimeData.builder()
                .type(WeatherAggregationEnum.DAY)
                .windMax(data.getWindMax())
                .temperature(data.getTemperatureAvg())
                .date(data.getDay())
                .humidity(data.getHumidityAvg())
                .rainTotal(data.getRainTotal())
                .station(station.getName())
                .build()
                : null;
    }

    @Override
    public WeatherTimeData getWeatherMonthOrNull(String year, String month, StationData station) {
        WeatherMonthSummaryData data = monthSummaryFacade.getMonthForStationOrNull(station.getCode(), month, year);
        return data != null
                ? WeatherTimeData.builder()
                .type(WeatherAggregationEnum.DAY)
                .windMax(data.getWindMax())
                .temperature(data.getTemperatureAvg())
                .date(String.format("%s-%s-01", year, month))
                .humidity(data.getHumidityAvg())
                .rainTotal(data.getRainTotal())
                .station(station.getName())
                .build()
                : null;
    }

    @Override
    public WeatherTimeData getWeatherYearOrNull(String year, StationData station) {
        WeatherYearSummaryData data = yearSummaryFacade.getYearForStationOrNull(station.getCode(), year);
        return data != null
                ? WeatherTimeData.builder()
                .type(WeatherAggregationEnum.DAY)
                .windMax(data.getWindMax())
                .temperature(data.getTemperatureAvg())
                .date(String.format("%s-01-01", year))
                .humidity(data.getHumidityAvg())
                .rainTotal(data.getRainTotal())
                .station(station.getName())
                .build()
                : null;
    }
}
