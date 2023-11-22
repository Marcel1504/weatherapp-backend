package me.marcelberger.weatherapp.consumer.facade.data.impl;

import me.marcelberger.weatherapp.consumer.data.request.SOPRequestData;
import me.marcelberger.weatherapp.consumer.facade.data.DataFacade;
import me.marcelberger.weatherapp.consumer.service.data.updater.impl.SOPDataUpdaterServiceImpl;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.station.StationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SOPDataFacadeImpl extends DataFacade<SOPRequestData> {

    @Autowired
    private SOPDataUpdaterServiceImpl dataUpdaterService;

    @Override
    protected StationEntity getStationFromData(SOPRequestData sopRequestData) {
        throw new UnsupportedOperationException("Station cannot be loaded from SOPRequestData");
    }

    @Override
    protected void update(StationEntity station, SOPRequestData sopRequestData) {
        dataUpdaterService.update(station, sopRequestData);
    }

    @Override
    protected StationTypeEnum stationType() {
        return StationTypeEnum.SOIL;
    }
}
