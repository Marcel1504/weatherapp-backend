package me.marcelberger.weatherapp.api.facade.weather.hour;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.api.service.sort.weather.WeatherSortService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherHourEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WeatherHourFacadeImpl implements WeatherHourFacade {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService msg;

    @Autowired
    private WeatherHourRepository weatherHourRepository;

    @Autowired
    private DataMapper<WeatherHourEntity, WeatherSummaryData> weatherHourDataMapper;

    @Autowired
    private WeatherSortService weatherSortService;

    @Override
    public PageData<WeatherSummaryData> getHoursOfDayForStation(String stationCode, String day, WeatherSortEnum sort) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return weatherHourDataMapper.mapPage(weatherHourRepository.findAllByStationAndDay(
                PageRequest.of(0, 24, weatherSortService.forHour(sort)),
                station,
                day));
    }
}
