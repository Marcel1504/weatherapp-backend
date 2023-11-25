package me.marcelberger.weatherapp.core.facade.summary.hour;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.summary.hour.HourSummaryRepository;
import me.marcelberger.weatherapp.core.service.sort.SortService;
import me.marcelberger.weatherapp.core.service.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

public abstract class HourSummaryFacade<SOURCE, TARGET, SORT> {

    @Autowired
    private StationService stationService;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private HourSummaryRepository<SOURCE> hourSummaryRepository;

    @Autowired
    private SortService<SORT> sortService;

    public PageData<TARGET> getHoursOfDayForStation(String stationCode, String day, SORT sort) {
        StationEntity station = stationService.getByStationCode(stationCode);
        return dataMapper.mapToPage(hourSummaryRepository.findAllByStationAndDay(
                PageRequest.of(0, 24, sortService.forHour(sort)),
                station,
                day));
    }
}
