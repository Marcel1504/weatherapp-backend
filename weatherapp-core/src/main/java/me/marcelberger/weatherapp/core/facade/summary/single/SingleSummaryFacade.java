package me.marcelberger.weatherapp.core.facade.summary.single;


import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.summary.single.SingleSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SingleSummaryFacade<SOURCE, TARGET> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private SingleSummaryRepository<SOURCE> singleSummaryRepository;

    public TARGET getLatest(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00020, stationCode);
        }
        SOURCE data = singleSummaryRepository.findLatestByStationId(station.getId());
        if (data == null) {
            throw new CoreException(getSingleSummaryNotFoundErrorCode(), station.getCode());
        }
        return dataMapper.map(data);
    }

    protected abstract ErrorCodeEnum getSingleSummaryNotFoundErrorCode();
}
