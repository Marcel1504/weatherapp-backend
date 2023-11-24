package me.marcelberger.weatherapp.assistant.service.openai.function;

import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionResultData;
import me.marcelberger.weatherapp.assistant.data.weather.WeatherRecordData;
import me.marcelberger.weatherapp.assistant.data.weather.WeatherRecordFunctionCallData;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherRecordEnum;
import me.marcelberger.weatherapp.assistant.service.weather.record.WeatherRecordService;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.facade.station.StationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAIWeatherRecordFunctionServiceImpl extends OpenAIFunctionService<WeatherRecordFunctionCallData> {

    @Autowired
    private StationFacade stationFacade;

    @Autowired
    private WeatherRecordService weatherRecordService;

    @Override
    public OpenAIFunctionEnum getFunction() {
        return OpenAIFunctionEnum.WEATHER_RECORD;
    }

    @Override
    protected Class<WeatherRecordFunctionCallData> getFunctionCallDataClass() {
        return WeatherRecordFunctionCallData.class;
    }

    @Override
    protected OpenAIFunctionResultData executeByFunctionCalData(WeatherRecordFunctionCallData data) {
        return data.getStation() != null
                ? executeForSpecificStation(data)
                : executeForAllStations(data);
    }

    private OpenAIFunctionResultData executeForAllStations(WeatherRecordFunctionCallData data) {
        WeatherRecordData weatherRecord = switch (data.getAggregation()) {
            case DAY -> weatherRecordService.getWeatherDayForAllStationsOrNull(data.getType());
            case MONTH -> weatherRecordService.getWeatherMonthForAllStationsOrNull(data.getType());
            case YEAR -> weatherRecordService.getWeatherYearForAllStationsOrNull(data.getType());
        };
        return weatherRecord != null
                ? buildForWeatherRecordData(data.getType(), weatherRecord, null)
                : buildNoResultData();
    }

    private OpenAIFunctionResultData executeForSpecificStation(WeatherRecordFunctionCallData data) {
        StationData station = stationFacade.searchClosestStationMatchByName(data.getStation());
        WeatherRecordData weatherRecord = switch (data.getAggregation()) {
            case DAY -> weatherRecordService.getWeatherDayOrNull(data.getType(), station);
            case MONTH -> weatherRecordService.getWeatherMonthOrNull(data.getType(), station);
            case YEAR -> weatherRecordService.getWeatherYearOrNull(data.getType(), station);
        };
        return weatherRecord != null
                ? buildForWeatherRecordData(data.getType(), weatherRecord, station.getName())
                : buildNoResultData();
    }

    private OpenAIFunctionResultData buildForWeatherRecordData(WeatherRecordEnum type,
                                                               WeatherRecordData data,
                                                               String station) {
        String messageShort = switch (type) {
            case HOTTEST -> String.format("tempMax: %s C", data.getTemperatureMax());
            case COLDEST -> String.format("tempMin: %s C", data.getTemperatureMin());
            case MOST_RAIN -> String.format("rain: %s l/m²", data.getRainTotal());
            case STRONGEST_WIND -> String.format("windMax: %s kph", data.getWindMax());
        };
        messageShort += station != null ? String.format(", station: %s", station) : "";
        messageShort += data.getDate() != null ? String.format(", %s", data.getDate()) : "";
        return OpenAIFunctionResultData.builder()
                .resultShort(messageShort)
                .resultLong(writeDataAsString(data))
                .build();
    }
}
