package me.marcelberger.weatherapp.assistant.service.openai.function;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionResultData;
import me.marcelberger.weatherapp.assistant.data.weather.WeatherTimeData;
import me.marcelberger.weatherapp.assistant.data.weather.WeatherTimeFunctionCallData;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import me.marcelberger.weatherapp.assistant.service.weather.time.WeatherTimeService;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.facade.station.StationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;


@Service
@Slf4j
public class OpenAIWeatherTimeFunctionServiceImpl extends OpenAIFunctionService<WeatherTimeFunctionCallData> {

    @Autowired
    private WeatherTimeService weatherTimeService;

    @Autowired
    private StationFacade stationFacade;

    @Override
    public OpenAIFunctionEnum getFunction() {
        return OpenAIFunctionEnum.WEATHER_TIME;
    }

    @Override
    protected Class<WeatherTimeFunctionCallData> getFunctionCallDataClass() {
        return WeatherTimeFunctionCallData.class;
    }

    @Override
    protected OpenAIFunctionResultData executeByFunctionCalData(WeatherTimeFunctionCallData data) {
        StationData station = stationFacade.searchClosestStationMatchByName(data.getStation());
        String date = data.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String month = data.getDate().format(DateTimeFormatter.ofPattern("MM"));
        String year = data.getDate().format(DateTimeFormatter.ofPattern("yyyy"));
        WeatherTimeData weatherTime = switch (data.getAggregation()) {
            case DAY -> weatherTimeService.getWeatherDayOrNull(date, station);
            case MONTH -> weatherTimeService.getWeatherMonthOrNull(year, month, station);
            case YEAR -> weatherTimeService.getWeatherYearOrNull(year, station);
        };
        return weatherTime != null ? buildForWeatherTimeData(weatherTime) : buildNoResultData();
    }

    private OpenAIFunctionResultData buildForWeatherTimeData(WeatherTimeData weatherTime) {
        return OpenAIFunctionResultData.builder()
                .resultShort(String.format(
                        "temp: %s C, windMax: %s kph, rain: %s l/m²",
                        weatherTime.getTemperature(),
                        weatherTime.getWindMax(),
                        weatherTime.getRainTotal()))
                .resultLong(writeDataAsString(weatherTime))
                .build();
    }
}
