package me.marcelberger.weatherapp.api.facade.weather.month;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.api.service.sort.weather.WeatherSortService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherMonthEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WeatherMonthFacadeImpl implements WeatherMonthFacade {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService msg;

    @Autowired
    private WeatherMonthRepository weatherMonthRepository;

    @Autowired
    private DataMapper<WeatherMonthEntity, WeatherSummaryData> weatherMonthDataMapper;

    @Autowired
    private WeatherSortService weatherSortService;

    @Override
    public WeatherSummaryData getMonthForStation(String stationCode, String month, String year) {
        StationEntity station = getStation(stationCode);
        WeatherMonthEntity monthEntity = weatherMonthRepository.findByStationAndMonthAndYear(station, month, year);
        if (monthEntity == null) {
            throw new ServiceException(msg.get("weather.notFound"));
        }
        return weatherMonthDataMapper.map(monthEntity);
    }

    @Override
    public PageData<WeatherSummaryData> getMonthsOfYearForStation(String stationCode,
                                                                  Integer page,
                                                                  Integer size,
                                                                  String year,
                                                                  WeatherSortEnum sort) {
        StationEntity station = getStation(stationCode);
        Page<WeatherMonthEntity> data;
        if (year != null) {
            data = weatherMonthRepository.findAllByStationAndYear(
                    PageRequest.of(page, size, weatherSortService.forMonth(sort)),
                    station,
                    year);
        } else {
            data = weatherMonthRepository.findAllByStation(
                    PageRequest.of(page, size, weatherSortService.forMonth(sort)),
                    station);
        }
        return weatherMonthDataMapper.mapPage(data);
    }

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return station;
    }
}
