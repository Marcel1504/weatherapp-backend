package me.marcelberger.weatherapp.consumer.service.data.validator.impl;

import me.marcelberger.weatherapp.consumer.service.data.validator.DataValidatorService;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataValidatorServiceImpl implements DataValidatorService<WeatherSingleDataEntity> {

    @Value("${weatherapp.validation.temperature.max}")
    private double temperatureMax;

    @Value("${weatherapp.validation.temperature.min}")
    private double temperatureMin;

    @Value("${weatherapp.validation.humidity.max}")
    private double humidityMax;

    @Value("${weatherapp.validation.humidity.min}")
    private double humidityMin;

    @Value("${weatherapp.validation.pressure.max}")
    private double pressureMax;

    @Value("${weatherapp.validation.pressure.min}")
    private double pressureMin;

    @Value("${weatherapp.validation.wind.max}")
    private double windMax;

    @Value("${weatherapp.validation.wind.min}")
    private double windMin;

    @Value("${weatherapp.validation.windDirection.max}")
    private double windDirectionMax;

    @Value("${weatherapp.validation.windDirection.min}")
    private double windDirectionMin;

    @Value("${weatherapp.validation.solarRadiation.max}")
    private double solarRadiationMax;

    @Value("${weatherapp.validation.solarRadiation.min}")
    private double solarRadiationMin;

    @Override
    public boolean isValid(WeatherSingleDataEntity element) {
        return element.getTimestamp() != null
                && isValidTemperature(element)
                && isValidHumidity(element)
                && isValidPressure(element)
                && isValidWind(element)
                && isValidWindDirection(element)
                && isValidRainDelta(element)
                && isValidRainRate(element)
                && isValidSolarRadiation(element);
    }

    private boolean isValidTemperature(WeatherSingleDataEntity w) {
        // temperature should always be provided and never be null
        return w.getTemperature() != null
                && (w.getTemperature() <= temperatureMax && w.getTemperature() >= temperatureMin);
    }

    private boolean isValidHumidity(WeatherSingleDataEntity w) {
        return w.getHumidity() == null
                || (w.getHumidity() <= humidityMax && w.getHumidity() >= humidityMin);
    }

    private boolean isValidPressure(WeatherSingleDataEntity w) {
        return w.getPressure() == null
                || (w.getPressure() <= pressureMax && w.getPressure() >= pressureMin);
    }

    private boolean isValidWind(WeatherSingleDataEntity w) {
        return w.getWind() == null
                || (w.getWind() <= windMax && w.getWind() >= windMin);
    }

    private boolean isValidWindDirection(WeatherSingleDataEntity w) {
        return w.getWindDirection() == null
                || (w.getWindDirection() <= windDirectionMax && w.getWind() >= windDirectionMin);
    }

    private boolean isValidRainDelta(WeatherSingleDataEntity w) {
        return w.getRainDelta() == null || w.getRainDelta() >= 0;
    }

    private boolean isValidRainRate(WeatherSingleDataEntity w) {
        return w.getRainRate() == null || w.getRainRate() >= 0;
    }

    private boolean isValidSolarRadiation(WeatherSingleDataEntity w) {
        return w.getSolarRadiation() == null
                || (w.getSolarRadiation() <= solarRadiationMax && w.getSolarRadiation() >= solarRadiationMin);
    }
}