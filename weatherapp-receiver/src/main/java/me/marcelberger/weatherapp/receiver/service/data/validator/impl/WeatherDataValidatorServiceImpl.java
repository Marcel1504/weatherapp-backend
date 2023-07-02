package me.marcelberger.weatherapp.receiver.service.data.validator.impl;

import me.marcelberger.weatherapp.core.entity.WeatherEntity;
import me.marcelberger.weatherapp.receiver.service.data.validator.DataValidatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataValidatorServiceImpl implements DataValidatorService<WeatherEntity> {

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
    public boolean isValid(WeatherEntity element) {
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

    private boolean isValidTemperature(WeatherEntity w) {
        return w.getTemperature() == null
                || (w.getTemperature() <= temperatureMax && w.getTemperature() >= temperatureMin);
    }

    private boolean isValidHumidity(WeatherEntity w) {
        return w.getHumidity() == null
                || (w.getHumidity() <= humidityMax && w.getHumidity() >= humidityMin);
    }

    private boolean isValidPressure(WeatherEntity w) {
        return w.getPressure() == null
                || (w.getPressure() <= pressureMax && w.getPressure() >= pressureMin);
    }

    private boolean isValidWind(WeatherEntity w) {
        return w.getWind() == null
                || (w.getWind() <= windMax && w.getWind() >= windMin);
    }

    private boolean isValidWindDirection(WeatherEntity w) {
        return w.getWindDirection() == null
                || (w.getWindDirection() <= windDirectionMax && w.getWind() >= windDirectionMin);
    }

    private boolean isValidRainDelta(WeatherEntity w) {
        return w.getRainDelta() == null || w.getRainDelta() >= 0;
    }

    private boolean isValidRainRate(WeatherEntity w) {
        return w.getRainRate() == null || w.getRainRate() >= 0;
    }

    private boolean isValidSolarRadiation(WeatherEntity w) {
        return w.getSolarRadiation() == null
                || (w.getSolarRadiation() <= solarRadiationMax && w.getSolarRadiation() >= solarRadiationMin);
    }
}
