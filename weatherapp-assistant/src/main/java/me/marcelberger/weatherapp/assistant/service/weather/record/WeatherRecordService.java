package me.marcelberger.weatherapp.assistant.service.weather.record;

import me.marcelberger.weatherapp.assistant.data.weather.record.WeatherRecordData;
import me.marcelberger.weatherapp.assistant.enumeration.weather.WeatherRecordEnum;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;

public interface WeatherRecordService {
    WeatherRecordData getWeatherDayOrNull(WeatherRecordEnum type, StationEntity station);

    WeatherRecordData getWeatherDayForAllStationsOrNull(WeatherRecordEnum type);

    WeatherRecordData getWeatherMonthOrNull(WeatherRecordEnum type, StationEntity station);

    WeatherRecordData getWeatherMonthForAllStationsOrNull(WeatherRecordEnum type);

    WeatherRecordData getWeatherYearOrNull(WeatherRecordEnum type, StationEntity station);

    WeatherRecordData getWeatherYearForAllStationsOrNull(WeatherRecordEnum type);
}
