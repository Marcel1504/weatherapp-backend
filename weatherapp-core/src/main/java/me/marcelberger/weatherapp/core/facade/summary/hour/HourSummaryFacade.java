package me.marcelberger.weatherapp.core.facade.summary.hour;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.summary.hour.HourSummaryRepository;
import me.marcelberger.weatherapp.core.service.sort.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

public abstract class HourSummaryFacade<SOURCE, TARGET, SORT> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private HourSummaryRepository<SOURCE> hourSummaryRepository;

    @Autowired
    private SortService<SORT> sortService;

    public PageData<TARGET> getHoursOfDayForStation(String stationCode, String day, SORT sort) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00020, stationCode);
        }
        return dataMapper.mapPage(hourSummaryRepository.findAllByStationAndDay(
                PageRequest.of(0, 24, sortService.forHour(sort)),
                station,
                day));
    }
}
