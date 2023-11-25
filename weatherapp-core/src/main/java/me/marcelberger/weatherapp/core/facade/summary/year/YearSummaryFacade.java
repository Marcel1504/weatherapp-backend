package me.marcelberger.weatherapp.core.facade.summary.year;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.summary.year.YearSummaryRepository;
import me.marcelberger.weatherapp.core.service.sort.SortService;
import me.marcelberger.weatherapp.core.service.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class YearSummaryFacade<SOURCE, TARGET, SORT> {

    @Autowired
    private StationService stationService;

    @Autowired
    private YearSummaryRepository<SOURCE> yearSummaryRepository;

    @Autowired
    private SortService<SORT> sortService;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    public TARGET getYearForStation(String stationCode, String year) {
        StationEntity station = stationService.getByStationCode(stationCode);
        SOURCE yearSummaryEntity = yearSummaryRepository.findByStationAndYear(station, year);
        if (yearSummaryEntity == null) {
            throw new CoreError(getYearSummaryNotFoundErrorCode(), "Year summary for year %s not found", year);
        }
        return dataMapper.map(yearSummaryEntity);
    }

    public TARGET getYearForStationOrNull(String stationCode, String year) {
        StationEntity station = stationService.getByStationCode(stationCode);
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
        StationEntity station = stationService.getByStationCode(stationCode);
        return dataMapper.mapToPage(yearSummaryRepository.findAllByStation(
                PageRequest.of(page, size, sortService.forYear(sort)),
                station));
    }

    public PageData<TARGET> getAllYears(Integer page, Integer size, SORT sort) {
        Page<SOURCE> data = yearSummaryRepository
                .findAll(PageRequest.of(page, size, sortService.forDay(sort)));
        return dataMapper.mapToPage(data);
    }

    protected abstract CoreError.Code getYearSummaryNotFoundErrorCode();

}
