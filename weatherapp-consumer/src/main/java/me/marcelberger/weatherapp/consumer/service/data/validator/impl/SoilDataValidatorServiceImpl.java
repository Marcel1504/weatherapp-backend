package me.marcelberger.weatherapp.consumer.service.data.validator.impl;

import me.marcelberger.weatherapp.consumer.service.data.validator.DataValidatorService;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SoilDataValidatorServiceImpl implements DataValidatorService<SoilSingleSummaryEntity> {

    @Value("${weatherapp.validation.temperature.max}")
    private double temperatureMax;

    @Value("${weatherapp.validation.temperature.min}")
    private double temperatureMin;

    @Override
    public boolean isValid(SoilSingleSummaryEntity element) {
        return element.getTimestamp() != null
                && isValidTemperature(element.getTemperature50cm())
                && isValidTemperature(element.getTemperature100cm())
                && isValidTemperature(element.getTemperature200cm());
    }

    private boolean isValidTemperature(Double temperature) {
        return temperature == null || (temperature <= temperatureMax && temperature >= temperatureMin);
    }
}
