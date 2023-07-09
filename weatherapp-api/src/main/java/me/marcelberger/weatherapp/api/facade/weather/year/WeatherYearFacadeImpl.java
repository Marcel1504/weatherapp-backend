package me.marcelberger.weatherapp.api.facade.weather.year;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.api.service.sort.weather.WeatherSortService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherYearEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WeatherYearFacadeImpl implements WeatherYearFacade {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService msg;

    @Autowired
    private WeatherYearRepository weatherYearRepository;

    @Autowired
    private WeatherSortService weatherSortService;

    @Autowired
    private DataMapper<WeatherYearEntity, WeatherSummaryData> weatherYearDataMapper;

    @Override
    public WeatherSummaryData getYearForStation(String stationCode, String year) {
        StationEntity station = getStation(stationCode);
        WeatherYearEntity yearEntity = weatherYearRepository.findByStationAndYear(station, year);
        if (yearEntity == null) {
            throw new ServiceException(msg.get("weather.notFound"));
        }
        return weatherYearDataMapper.map(yearEntity);
    }

    @Override
    public PageData<WeatherSummaryData> getAllYearsForStation(String stationCode,
                                                              Integer page,
                                                              Integer size,
                                                              WeatherSortEnum sort) {
        StationEntity station = getStation(stationCode);
        return weatherYearDataMapper.mapPage(weatherYearRepository.findAllByStation(
                PageRequest.of(page, size, weatherSortService.forYear(sort)),
                station));
    }

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return station;
    }
}
