package me.marcelberger.weatherapp.receiver.service.data.validator.impl;

import me.marcelberger.weatherapp.core.entity.soil.SoilEntity;
import me.marcelberger.weatherapp.receiver.service.data.validator.DataValidatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SoilDataValidatorServiceImpl implements DataValidatorService<SoilEntity> {

    @Value("${weatherapp.validation.temperature.max}")
    private double temperatureMax;

    @Value("${weatherapp.validation.temperature.min}")
    private double temperatureMin;

    @Override
    public boolean isValid(SoilEntity element) {
        return element.getTimestamp() != null
                && isValidTemperature(element.getTemperature50cm())
                && isValidTemperature(element.getTemperature100cm())
                && isValidTemperature(element.getTemperature200cm());
    }

    private boolean isValidTemperature(Double temperature) {
        return temperature == null || (temperature <= temperatureMax && temperature >= temperatureMin);
    }
}
