package me.marcelberger.weatherapp.receiver.facade.data.impl;

import me.marcelberger.weatherapp.core.entity.StationEntity;
import me.marcelberger.weatherapp.receiver.data.request.WEPRequestData;
import me.marcelberger.weatherapp.receiver.facade.data.DataFacade;
import me.marcelberger.weatherapp.receiver.service.data.updater.impl.WEPDataUpdaterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WEPDataFacadeImpl extends DataFacade<WEPRequestData> {

    @Autowired
    private WEPDataUpdaterServiceImpl dataUpdaterService;

    @Override
    protected StationEntity getStationFromData(WEPRequestData wepRequestData) {
        throw new UnsupportedOperationException("Station cannot be loaded from WEPRequestData");
    }

    @Override
    protected void update(StationEntity station, WEPRequestData wepRequestData) {
        dataUpdaterService.update(station, wepRequestData);
    }
}
