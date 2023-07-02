package me.marcelberger.weatherapp.receiver.service.data.updater.impl;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.receiver.data.request.WEPRequestData;
import me.marcelberger.weatherapp.receiver.service.data.updater.WeatherDataUpdaterService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WEPDataUpdaterServiceImpl extends WeatherDataUpdaterService<WEPRequestData> {

    @Override
    protected LocalDateTime getTimestamp(WEPRequestData data) {
        return data.getTimestamp();
    }

    @Override
    protected Double getTemperature(WEPRequestData data) {
        return data.getTemperature();
    }

    @Override
    protected Integer getHumidity(WEPRequestData data) {
        return data.getHumidity();
    }

    @Override
    protected Double getRainDelta(WEPRequestData data, StationEntity station) {
        return data.getRainfall();
    }

    @Override
    protected Double getRainRate(WEPRequestData data) {
        return data.getRainRate();
    }

    @Override
    protected Double getWind(WEPRequestData data) {
        return data.getWind();
    }

    @Override
    protected Integer getWindDirection(WEPRequestData data) {
        return data.getWindDirection();
    }

    @Override
    protected Double getPressure(WEPRequestData data) {
        return data.getPressure();
    }

    @Override
    protected Double getSolarRadiation(WEPRequestData data) {
        return null;
    }
}
