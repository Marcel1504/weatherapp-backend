package me.marcelberger.weatherapp.core.facade.summary.day;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.summary.day.DaySummaryRepository;
import me.marcelberger.weatherapp.core.service.sort.SortService;
import me.marcelberger.weatherapp.core.service.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class DaySummaryFacade<SOURCE, TARGET, SORT> {

    @Autowired
    private StationService stationService;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private SortService<SORT> sortService;

    @Autowired
    private DaySummaryRepository<SOURCE> daySummaryRepository;

    public PageData<TARGET> getDaysOfMonthForStation(String stationCode, String month, String year, SORT sort) {
        StationEntity station = stationService.getByStationCode(stationCode);
        Page<SOURCE> data = daySummaryRepository.findAllDaysOfMonthForStation(
                PageRequest.of(0, 31, sortService.forDay(sort)),
                station,
                String.format("%s-%s%%", year, month));
        return dataMapper.mapToPage(data);
    }

    public PageData<TARGET> getAllDaysForStation(String stationCode,
                                                 Integer page,
                                                 Integer size,
                                                 String startDay,
                                                 String endDay,
                                                 SORT sort) {
        StationEntity station = stationService.getByStationCode(stationCode);
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
        return dataMapper.mapToPage(data);
    }

    public PageData<TARGET> getAllDays(Integer page, Integer size, SORT sort) {
        Page<SOURCE> data = daySummaryRepository
                .findAllDays(PageRequest.of(page, size, sortService.forDay(sort)));
        return dataMapper.mapToPage(data);
    }

    public TARGET getDayForStation(String stationCode, String day) {
        StationEntity station = stationService.getByStationCode(stationCode);
        SOURCE dayEntity = daySummaryRepository.findByStationAndDay(station, day);
        if (dayEntity == null) {
            throw new CoreError(getDaySummaryNotFoundErrorCode(), "Day summary for day %s not found", day);
        }
        return dataMapper.map(dayEntity);
    }

    public TARGET getDayForStationOrNull(String stationCode, String day) {
        StationEntity station = stationService.getByStationCode(stationCode);
        SOURCE dayEntity = daySummaryRepository.findByStationAndDay(station, day);
        if (dayEntity == null) {
            return null;
        }
        return dataMapper.map(dayEntity);
    }

    protected abstract CoreError.Code getDaySummaryNotFoundErrorCode();
}
