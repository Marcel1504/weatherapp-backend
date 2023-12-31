package me.marcelberger.weatherapp.consumer.facade.data.impl;

import me.marcelberger.weatherapp.consumer.data.request.WEPRequestData;
import me.marcelberger.weatherapp.consumer.facade.data.DataFacade;
import me.marcelberger.weatherapp.consumer.service.data.updater.impl.WEPDataUpdaterServiceImpl;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.station.StationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WEPDataFacadeImpl extends DataFacade<WEPRequestData> {

    @Autowired
    private WEPDataUpdaterServiceImpl dataUpdaterService;

    @Override
    protected StationEntity getStationFromDataOrNull(WEPRequestData wepRequestData) {
        throw new UnsupportedOperationException("Station cannot be loaded from WEPRequestData");
    }

    @Override
    protected void update(StationEntity station, WEPRequestData wepRequestData) {
        dataUpdaterService.update(station, wepRequestData);
    }

    @Override
    protected StationTypeEnum stationType() {
        return StationTypeEnum.WEATHER;
    }
}
