package me.marcelberger.weatherapp.api.facade.data.year;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.DataResponseDto;
import me.marcelberger.weatherapp.api.mapper.data.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.api.service.sort.SortService;
import me.marcelberger.weatherapp.core.entity.data.DataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.data.year.YearDataRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

public abstract class YearDataFacade<SOURCE extends DataEntity, TARGET extends DataResponseDto, SORT> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService msg;

    @Autowired
    private YearDataRepository<SOURCE> yearDataRepository;

    @Autowired
    private SortService<SORT> sortService;

    @Autowired
    private DataMapper<SOURCE, TARGET> dataMapper;

    public TARGET getYearForStation(String stationCode, String year) {
        StationEntity station = getStation(stationCode);
        SOURCE yearEntity = yearDataRepository.findByStationAndYear(station, year);
        if (yearEntity == null) {
            throw new ServiceException(msg.get(getYearDataNotFoundMessageKey()));
        }
        return dataMapper.map(yearEntity);
    }

    public PageData<TARGET> getAllYearsForStation(String stationCode,
                                                  Integer page,
                                                  Integer size,
                                                  SORT sort) {
        StationEntity station = getStation(stationCode);
        return dataMapper.mapPage(yearDataRepository.findAllByStation(
                PageRequest.of(page, size, sortService.forYear(sort)),
                station));
    }

    protected abstract String getYearDataNotFoundMessageKey();

    private StationEntity getStation(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return station;
    }
}
