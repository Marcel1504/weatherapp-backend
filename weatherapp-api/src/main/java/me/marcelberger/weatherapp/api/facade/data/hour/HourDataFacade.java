package me.marcelberger.weatherapp.api.facade.data.hour;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.DataResponseDto;
import me.marcelberger.weatherapp.api.mapper.data.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.api.service.sort.SortService;
import me.marcelberger.weatherapp.core.entity.data.DataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.data.hour.HourDataRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

public abstract class HourDataFacade<SOURCE extends DataEntity, TARGET extends DataResponseDto, SORT> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService msg;

    @Autowired
    private DataMapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private HourDataRepository<SOURCE> hourDataRepository;

    @Autowired
    private SortService<SORT> sortService;

    public PageData<TARGET> getHoursOfDayForStation(String stationCode, String day, SORT sort) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return dataMapper.mapPage(hourDataRepository.findAllByStationAndDay(
                PageRequest.of(0, 24, sortService.forHour(sort)),
                station,
                day));
    }
}
