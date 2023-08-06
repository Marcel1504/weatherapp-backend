package me.marcelberger.weatherapp.consumer.service.data.updater.impl;

import me.marcelberger.weatherapp.consumer.data.request.SOPRequestData;
import me.marcelberger.weatherapp.consumer.service.data.updater.SoilDataUpdaterService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SOPDataUpdaterServiceImpl extends SoilDataUpdaterService<SOPRequestData> {

    @Override
    protected LocalDateTime getTimestamp(SOPRequestData data) {
        return data.getTimestamp();
    }

    @Override
    protected Double getTemperature50cm(SOPRequestData data) {
        return data.getTemperature3();
    }

    @Override
    protected Double getTemperature100cm(SOPRequestData data) {
        return data.getTemperature2();
    }

    @Override
    protected Double getTemperature200cm(SOPRequestData data) {
        return data.getTemperature1();
    }
}
