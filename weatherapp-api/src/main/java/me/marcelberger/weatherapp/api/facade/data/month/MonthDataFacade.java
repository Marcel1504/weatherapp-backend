package me.marcelberger.weatherapp.api.facade.data.month;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.DataResponseDto;
import me.marcelberger.weatherapp.api.mapper.data.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.api.service.sort.SortService;
import me.marcelberger.weatherapp.core.entity.data.DataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.data.month.MonthDataRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class MonthDataFacade<SOURCE extends DataEntity, TARGET extends DataResponseDto, SORT> {
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService msg;

    @Autowired
    private MonthDataRepository<SOURCE> monthDataRepository;

    @Autowired
    private DataMapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private SortService<SORT> sortService;

    public TARGET getMonthForStation(String stationCode, String month, String year) {
        StationEntity station = getStation(stationCode);
        SOURCE monthEntity = monthDataRepository.findByStationAndMonthAndYear(station, month, year);
        if (monthEntity == null) {
            throw new ServiceException(msg.get(getMonthDataNotFoundMessageKey()));
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
            data = monthDataRepository.findAllByStationAndYear(
                    PageRequest.of(page, size, sortService.forMonth(sort)),
                    station,
                    year);
        } else {
            data = monthDataRepository.findAllByStation(
                    PageRequest.of(page, size, sortService.forMonth(sort)),
                    station);
        }
        return dataMapper.mapPage(data);
    }

    protected abstract String getMonthDataNotFoundMessageKey();

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return station;
    }
}
