package me.marcelberger.weatherapp.core.facade.summary.single;


import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.summary.single.SingleSummaryRepository;
import me.marcelberger.weatherapp.core.service.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SingleSummaryFacade<SOURCE, TARGET> {

    @Autowired
    private StationService stationService;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private SingleSummaryRepository<SOURCE> singleSummaryRepository;

    public TARGET getLatest(String stationCode) {
        StationEntity station = stationService.getByStationCode(stationCode);
        SOURCE data = singleSummaryRepository.findLatestByStationId(station.getId());
        if (data == null) {
            throw new CoreError(getSingleSummaryNotFoundErrorCode(), "Latest single summary not found");
        }
        return dataMapper.map(data);
    }

    protected abstract CoreError.Code getSingleSummaryNotFoundErrorCode();
}
