package me.marcelberger.weatherapp.assistant.service.openai.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionResultData;
import me.marcelberger.weatherapp.assistant.data.weather.WeatherTimeData;
import me.marcelberger.weatherapp.assistant.data.weather.WeatherTimeFunctionCallData;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import me.marcelberger.weatherapp.assistant.exception.AssistantException;
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

    @Autowired
    private ObjectMapper objectMapper;

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
            case DAY -> weatherTimeService.getWeatherDay(date, station);
            case MONTH -> weatherTimeService.getWeatherMonth(year, month, station);
            case YEAR -> weatherTimeService.getWeatherYear(year, station);
        };
        return OpenAIFunctionResultData.builder()
                .resultShort(String.format(
                        "temp: %s C, windMax: %s kph, rain: %s l/m²",
                        weatherTime.getTemperature(),
                        weatherTime.getWindMax(),
                        weatherTime.getRainTotal()))
                .resultLong(getWeatherTimeJsonString(weatherTime))
                .build();
    }

    private String getWeatherTimeJsonString(WeatherTimeData data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new AssistantException("Can not process weather data");
        }
    }
}
