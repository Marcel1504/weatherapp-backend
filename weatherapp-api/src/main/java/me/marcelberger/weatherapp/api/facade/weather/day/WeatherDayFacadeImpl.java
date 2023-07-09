package me.marcelberger.weatherapp.api.facade.weather.day;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.api.service.sort.weather.WeatherSortService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherDayEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WeatherDayFacadeImpl implements WeatherDayFacade {

    @Autowired
    private MessageService msg;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private WeatherDayRepository weatherDayRepository;

    @Autowired
    private DataMapper<WeatherDayEntity, WeatherSummaryData> weatherDayDataMapper;

    @Autowired
    private WeatherSortService weatherSortService;

    @Override
    public PageData<WeatherSummaryData> getDaysOfMonthForStation(String stationCode, String month, String year) {
        StationEntity station = getStation(stationCode);
        Page<WeatherDayEntity> data = weatherDayRepository.findAllDaysOfMonthForStation(
                Pageable.unpaged(),
                station,
                String.format("%s-%s%%", year, month));
        return weatherDayDataMapper.mapPage(data);
    }

    @Override
    public PageData<WeatherSummaryData> getAllDaysForStation(String stationCode,
                                                             Integer page,
                                                             Integer size,
                                                             String startDay,
                                                             String endDay,
                                                             WeatherSortEnum sort) {
        StationEntity station = getStation(stationCode);
        Page<WeatherDayEntity> data;
        if (startDay != null && endDay != null) {
            data = weatherDayRepository.findAllDaysInRangeForStation(
                    PageRequest.of(page, size, weatherSortService.forDay(sort)),
                    station,
                    startDay,
                    endDay);
        } else if (startDay != null) {
            data = weatherDayRepository.findAllDaysFromStartForStation(
                    PageRequest.of(page, size, weatherSortService.forDay(sort)),
                    station,
                    startDay);
        } else if (endDay != null) {
            data = weatherDayRepository.findAllDaysToEndForStation(
                    PageRequest.of(page, size, weatherSortService.forDay(sort)),
                    station,
                    endDay);
        } else {
            data = weatherDayRepository.findAllDaysForStation(
                    PageRequest.of(page, size, weatherSortService.forDay(sort)),
                    station);
        }
        return weatherDayDataMapper.mapPage(data);
    }

    @Override
    public WeatherSummaryData getDayForStation(String stationCode, String day) {
        StationEntity station = getStation(stationCode);
        WeatherDayEntity dayEntity = weatherDayRepository.findByStationAndDay(station, day);
        if (dayEntity == null) {
            throw new ServiceException(msg.get("weather.notFound"));
        }
        return weatherDayDataMapper.map(dayEntity);
    }

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return station;
    }
}
