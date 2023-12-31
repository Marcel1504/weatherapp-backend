package me.marcelberger.weatherapp.assistant.mapper;

import me.marcelberger.weatherapp.assistant.data.weather.time.WeatherTimeData;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherAggregationEnum;
import me.marcelberger.weatherapp.core.data.summary.WeatherSummaryData;
import me.marcelberger.weatherapp.core.data.summary.day.WeatherDaySummaryData;
import me.marcelberger.weatherapp.core.data.summary.month.WeatherMonthSummaryData;
import me.marcelberger.weatherapp.core.data.summary.year.WeatherYearSummaryData;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class WeatherSummaryDataWeatherTimeDataMapperImpl implements Mapper<WeatherSummaryData, WeatherTimeData> {

    @Override
    public WeatherTimeData map(WeatherSummaryData object) {
        return WeatherTimeData.builder()
                .date(determineDate(object))
                .temperature(object.getTemperatureAvg())
                .rainTotal(object.getRainTotal())
                .windMax(object.getWindMax())
                .station(object.getStationName())
                .humidity(object.getHumidityAvg())
                .aggregation(determineAggregation(object))
                .build();
    }

    private String determineDate(WeatherSummaryData summary) {
        if (summary instanceof WeatherDaySummaryData data) {
            return data.getDay();
        }
        if (summary instanceof WeatherMonthSummaryData data) {
            return String.format("%s-%s-01", data.getYear(), data.getMonth());
        }
        if (summary instanceof WeatherYearSummaryData data) {
            return String.format("%s-01-01", data.getYear());
        }
        return null;
    }

    private WeatherAggregationEnum determineAggregation(WeatherSummaryData summary) {
        if (summary instanceof WeatherDaySummaryData) {
            return WeatherAggregationEnum.DAY;
        }
        if (summary instanceof WeatherMonthSummaryData) {
            return WeatherAggregationEnum.MONTH;
        }
        if (summary instanceof WeatherYearSummaryData) {
            return WeatherAggregationEnum.YEAR;
        }
        return null;
    }
}
