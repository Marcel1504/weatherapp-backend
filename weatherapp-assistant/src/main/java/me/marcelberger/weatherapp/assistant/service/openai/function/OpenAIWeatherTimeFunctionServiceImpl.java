package me.marcelberger.weatherapp.assistant.service.openai.function;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionResultData;
import me.marcelberger.weatherapp.assistant.data.weather.WeatherTimeFunctionData;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class OpenAIWeatherTimeFunctionServiceImpl extends OpenAIFunctionService<WeatherTimeFunctionData> {

    @Override
    public OpenAIFunctionEnum getFunction() {
        return OpenAIFunctionEnum.WEATHER_TIME;
    }

    @Override
    protected Class<WeatherTimeFunctionData> getFunctionCallDataClass() {
        return WeatherTimeFunctionData.class;
    }

    @Override
    protected OpenAIFunctionResultData executeByFunctionCalData(WeatherTimeFunctionData weatherTimeFunctionData) {
        return null;
    }
}
