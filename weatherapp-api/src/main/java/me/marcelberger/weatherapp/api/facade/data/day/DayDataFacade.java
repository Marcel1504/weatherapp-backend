package me.marcelberger.weatherapp.api.facade.data.day;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.DataResponseDto;
import me.marcelberger.weatherapp.api.mapper.data.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.api.service.sort.SortService;
import me.marcelberger.weatherapp.core.entity.data.DataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.data.day.DayDataRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class DayDataFacade<SOURCE extends DataEntity, TARGET extends DataResponseDto, SORT> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private DataMapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private MessageService msg;

    @Autowired
    private SortService<SORT> sortService;

    @Autowired
    private DayDataRepository<SOURCE> dayRepository;

    public PageData<TARGET> getDaysOfMonthForStation(String stationCode, String month, String year) {
        StationEntity station = getStation(stationCode);
        Page<SOURCE> data = dayRepository.findAllDaysOfMonthForStation(
                Pageable.unpaged(),
                station,
                String.format("%s-%s%%", year, month));
        return dataMapper.mapPage(data);
    }

    public PageData<TARGET> getAllDaysForStation(String stationCode,
                                                 Integer page,
                                                 Integer size,
                                                 String startDay,
                                                 String endDay,
                                                 SORT sort) {
        StationEntity station = getStation(stationCode);
        Page<SOURCE> data;
        if (startDay != null && endDay != null) {
            data = dayRepository.findAllDaysInRangeForStation(
                    PageRequest.of(page, size, sortService.forDay(sort)),
                    station,
                    startDay,
                    endDay);
        } else if (startDay != null) {
            data = dayRepository.findAllDaysFromStartDayForStation(
                    PageRequest.of(page, size, sortService.forDay(sort)),
                    station,
                    startDay);
        } else if (endDay != null) {
            data = dayRepository.findAllDaysToEndDayForStation(
                    PageRequest.of(page, size, sortService.forDay(sort)),
                    station,
                    endDay);
        } else {
            data = dayRepository.findAllDaysForStation(
                    PageRequest.of(page, size, sortService.forDay(sort)),
                    station);
        }
        return dataMapper.mapPage(data);
    }

    public TARGET getDayForStation(String stationCode, String day) {
        StationEntity station = getStation(stationCode);
        SOURCE dayEntity = dayRepository.findByStationAndDay(station, day);
        if (dayEntity == null) {
            throw new ServiceException(msg.get(getDayDataNotFoundMessageKey()));
        }
        return dataMapper.map(dayEntity);
    }

    protected abstract String getDayDataNotFoundMessageKey();

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return station;
    }
}
