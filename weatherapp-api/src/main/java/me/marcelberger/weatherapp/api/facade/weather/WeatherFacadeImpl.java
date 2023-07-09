package me.marcelberger.weatherapp.api.facade.weather;

import me.marcelberger.weatherapp.api.data.weather.WeatherData;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.WeatherEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.weather.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherFacadeImpl implements WeatherFacade {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService msg;

    @Autowired
    private DataMapper<WeatherEntity, WeatherData> weatherDataMapper;

    @Override
    public WeatherData getLatest(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        WeatherEntity weather = weatherRepository.findLatestForStationId(station.getId());
        if (weather == null) {
            throw new ServiceException(msg.get("weather.notFound", stationCode));
        }
        return weatherDataMapper.map(weather);
    }
}
