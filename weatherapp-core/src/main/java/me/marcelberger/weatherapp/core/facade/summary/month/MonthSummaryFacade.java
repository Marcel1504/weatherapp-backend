package me.marcelberger.weatherapp.core.facade.summary.month;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.summary.month.MonthSummaryRepository;
import me.marcelberger.weatherapp.core.service.sort.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class MonthSummaryFacade<SOURCE, TARGET, SORT> {
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MonthSummaryRepository<SOURCE> monthSummaryRepository;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private SortService<SORT> sortService;

    public TARGET getMonthForStation(String stationCode, String month, String year) {
        StationEntity station = getStation(stationCode);
        SOURCE monthEntity = monthSummaryRepository.findByStationAndMonthAndYear(station, month, year);
        if (monthEntity == null) {
            throw new CoreException(getMonthSummaryNotFoundErrorCode(), year, month, station.getCode());
        }
        return dataMapper.map(monthEntity);
    }

    public TARGET getMonthForStationOrNull(String stationCode, String month, String year) {
        StationEntity station = getStation(stationCode);
        SOURCE monthEntity = monthSummaryRepository.findByStationAndMonthAndYear(station, month, year);
        if (monthEntity == null) {
            return null;
        }
        return dataMapper.map(monthEntity);
    }

    public PageData<TARGET> getMonthsOfYearForStation(String stationCode,
                                                      Integer page,
                                                      Integer size,
                                                      String year,
                                                      SORT sort) {
        StationEntity station = getStation(stationCode);
        Page<SOURCE> data;
        if (year != null) {
            data = monthSummaryRepository.findAllByStationAndYear(
                    PageRequest.of(page, size, sortService.forMonth(sort)),
                    station,
                    year);
        } else {
            data = monthSummaryRepository.findAllByStation(
                    PageRequest.of(page, size, sortService.forMonth(sort)),
                    station);
        }
        return dataMapper.mapToPage(data);
    }

    public PageData<TARGET> getAllMonthsForStation(String stationCode, Integer page, Integer size, SORT sort) {
        StationEntity station = getStation(stationCode);
        Page<SOURCE> data = monthSummaryRepository
                .findAllByStation(PageRequest.of(page, size, sortService.forDay(sort)), station);
        return dataMapper.mapToPage(data);
    }

    public PageData<TARGET> getAllMonths(Integer page, Integer size, SORT sort) {
        Page<SOURCE> data = monthSummaryRepository
                .findAll(PageRequest.of(page, size, sortService.forDay(sort)));
        return dataMapper.mapToPage(data);
    }

    protected abstract ErrorCodeEnum getMonthSummaryNotFoundErrorCode();

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00020, stationCode);
        }
        return station;
    }
}
