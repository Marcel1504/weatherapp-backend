package me.marcelberger.weatherapp.receiver.facade.data.impl;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.receiver.data.request.SOPRequestData;
import me.marcelberger.weatherapp.receiver.facade.data.DataFacade;
import me.marcelberger.weatherapp.receiver.service.data.updater.impl.SOPDataUpdaterServiceImpl;
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
}
