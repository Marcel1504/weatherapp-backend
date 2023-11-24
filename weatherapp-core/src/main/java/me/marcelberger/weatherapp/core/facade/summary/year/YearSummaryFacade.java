package me.marcelberger.weatherapp.core.facade.summary.year;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.summary.year.YearSummaryRepository;
import me.marcelberger.weatherapp.core.service.sort.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class YearSummaryFacade<SOURCE, TARGET, SORT> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private YearSummaryRepository<SOURCE> yearSummaryRepository;

    @Autowired
    private SortService<SORT> sortService;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    public TARGET getYearForStation(String stationCode, String year) {
        StationEntity station = getStation(stationCode);
        SOURCE yearSummaryEntity = yearSummaryRepository.findByStationAndYear(station, year);
        if (yearSummaryEntity == null) {
            throw new CoreException(getYearSummaryNotFoundErrorCode(), year, station.getCode());
        }
        return dataMapper.map(yearSummaryEntity);
    }

    public TARGET getYearForStationOrNull(String stationCode, String year) {
        StationEntity station = getStation(stationCode);
        SOURCE yearSummaryEntity = yearSummaryRepository.findByStationAndYear(station, year);
        if (yearSummaryEntity == null) {
            return null;
        }
        return dataMapper.map(yearSummaryEntity);
    }

    public PageData<TARGET> getAllYearsForStation(String stationCode,
                                                  Integer page,
                                                  Integer size,
                                                  SORT sort) {
        StationEntity station = getStation(stationCode);
        return dataMapper.mapPage(yearSummaryRepository.findAllByStation(
                PageRequest.of(page, size, sortService.forYear(sort)),
                station));
    }

    public PageData<TARGET> getAllYears(Integer page, Integer size, SORT sort) {
        Page<SOURCE> data = yearSummaryRepository
                .findAll(PageRequest.of(page, size, sortService.forDay(sort)));
        return dataMapper.mapPage(data);
    }

    protected abstract ErrorCodeEnum getYearSummaryNotFoundErrorCode();

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00020, stationCode);
        }
        return station;
    }
}
