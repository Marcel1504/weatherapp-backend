package me.marcelberger.weatherapp.assistant.service.weather.record;

import me.marcelberger.weatherapp.assistant.data.weather.WeatherRecordData;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherRecordEnum;
import me.marcelberger.weatherapp.core.data.station.StationData;

public interface WeatherRecordService {
    WeatherRecordData getWeatherDayOrNull(WeatherRecordEnum type, StationData station);

    WeatherRecordData getWeatherDayForAllStationsOrNull(WeatherRecordEnum type);

    WeatherRecordData getWeatherMonthOrNull(WeatherRecordEnum type, StationData station);

    WeatherRecordData getWeatherMonthForAllStationsOrNull(WeatherRecordEnum type);

    WeatherRecordData getWeatherYearOrNull(WeatherRecordEnum type, StationData station);

    WeatherRecordData getWeatherYearForAllStationsOrNull(WeatherRecordEnum type);
}
