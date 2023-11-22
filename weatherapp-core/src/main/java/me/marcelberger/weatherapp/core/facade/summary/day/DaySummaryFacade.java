package me.marcelberger.weatherapp.core.facade.summary.day;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.summary.day.DaySummaryRepository;
import me.marcelberger.weatherapp.core.service.sort.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class DaySummaryFacade<SOURCE, TARGET, SORT> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private SortService<SORT> sortService;

    @Autowired
    private DaySummaryRepository<SOURCE> daySummaryRepository;

    public PageData<TARGET> getDaysOfMonthForStation(String stationCode, String month, String year, SORT sort) {
        StationEntity station = getStation(stationCode);
        Page<SOURCE> data = daySummaryRepository.findAllDaysOfMonthForStation(
                PageRequest.of(0, 31, sortService.forDay(sort)),
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
            data = daySummaryRepository.findAllDaysInRangeForStation(
                    PageRequest.of(page, size, sortService.forDay(sort)),
                    station,
                    startDay,
                    endDay);
        } else if (startDay != null) {
            data = daySummaryRepository.findAllDaysFromStartDayForStation(
                    PageRequest.of(page, size, sortService.forDay(sort)),
                    station,
                    startDay);
        } else if (endDay != null) {
            data = daySummaryRepository.findAllDaysToEndDayForStation(
                    PageRequest.of(page, size, sortService.forDay(sort)),
                    station,
                    endDay);
        } else {
            data = daySummaryRepository.findAllDaysForStation(
                    PageRequest.of(page, size, sortService.forDay(sort)),
                    station);
        }
        return dataMapper.mapPage(data);
    }

    public TARGET getDayForStation(String stationCode, String day) {
        StationEntity station = getStation(stationCode);
        SOURCE dayEntity = daySummaryRepository.findByStationAndDay(station, day);
        if (dayEntity == null) {
            throw new CoreException(getDaySummaryNotFoundErrorCode(), day, station.getCode());
        }
        return dataMapper.map(dayEntity);
    }

    protected abstract ErrorCodeEnum getDaySummaryNotFoundErrorCode();

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00020, stationCode);
        }
        return station;
    }
}
