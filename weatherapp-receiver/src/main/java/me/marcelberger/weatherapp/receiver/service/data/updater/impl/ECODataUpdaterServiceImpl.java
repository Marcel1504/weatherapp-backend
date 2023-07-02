package me.marcelberger.weatherapp.receiver.service.data.updater.impl;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationParameterEntity;
import me.marcelberger.weatherapp.core.repository.station.StationParameterRepository;
import me.marcelberger.weatherapp.core.service.math.MathService;
import me.marcelberger.weatherapp.receiver.service.data.updater.WeatherDataUpdaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@Slf4j
public class ECODataUpdaterServiceImpl extends WeatherDataUpdaterService<Map<String, String>> {

    @Autowired
    private MathService mathService;

    @Autowired
    private StationParameterRepository stationParameterRepository;

    @Value("${weatherapp.station.vendor.ecowitt.timestamp}")
    private String timestampKey;

    @Value("${weatherapp.station.vendor.ecowitt.temperature}")
    private String temperatureKey;

    @Value("${weatherapp.station.vendor.ecowitt.humidity}")
    private String humidityKey;

    @Value("${weatherapp.station.vendor.ecowitt.pressure}")
    private String pressureKey;

    @Value("${weatherapp.station.vendor.ecowitt.wind}")
    private String windKey;

    @Value("${weatherapp.station.vendor.ecowitt.windDirection}")
    private String windDirectionKey;

    @Value("${weatherapp.station.vendor.ecowitt.rainTotal}")
    private String rainTotalKey;

    @Value("${weatherapp.station.vendor.ecowitt.rainRate}")
    private String rainRateKey;

    @Value("${weatherapp.station.vendor.ecowitt.solarRadiation}")
    private String solarRadiationKey;

    @Value("${weatherapp.station.parameter.rainCount}")
    private String rainCountParameter;

    @Override
    protected LocalDateTime getTimestamp(Map<String, String> data) {
        try {
            return ZonedDateTime
                    .parse(
                            data.get(timestampKey),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC))
                    .withZoneSameInstant(ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (Exception e) {
            logWarning(timestampKey, data.get(timestampKey));
            return null;
        }
    }

    @Override
    protected Double getTemperature(Map<String, String> data) {
        try {
            String temperatureFahrenheit = data.get(temperatureKey);
            return mathService
                    .round((Double.parseDouble(temperatureFahrenheit) - 32) * (5.0 / 9.0), 1);
        } catch (Exception e) {
            logWarning(temperatureKey, data.get(temperatureKey));
            return null;
        }
    }

    @Override
    protected Integer getHumidity(Map<String, String> data) {
        try {
            return Integer.parseInt(data.get(humidityKey));
        } catch (Exception e) {
            logWarning(humidityKey, data.get(humidityKey));
            return null;
        }
    }

    @Override
    protected Double getRainDelta(Map<String, String> data, StationEntity station) {
        StationParameterEntity parameter = getRainCountStationParameter(station);
        Double currentRainCount = getCurrentRainCount(parameter);
        Double newRainCount = getNewRainCount(data);
        double rainDelta;

        if (currentRainCount == null) {
            currentRainCount = 0.0;
        }

        if (newRainCount == null || (newRainCount.equals(currentRainCount))) {
            rainDelta = 0.0;
            parameter.setValue(Double.toString(currentRainCount));
        } else if (newRainCount < currentRainCount) {
            rainDelta = newRainCount;
            parameter.setValue(Double.toString(newRainCount));
        } else {
            rainDelta = newRainCount - currentRainCount;
            parameter.setValue(Double.toString(newRainCount));
        }
        stationParameterRepository.save(parameter);
        return mathService.round(rainDelta, 1);
    }

    @Override
    protected Double getRainRate(Map<String, String> data) {
        try {
            String rainRate = data.get(rainRateKey);
            return Double.parseDouble(rainRate) * 25.4;
        } catch (Exception e) {
            logWarning(rainRateKey, data.get(rainRateKey));
            return null;
        }
    }

    @Override
    protected Double getWind(Map<String, String> data) {
        try {
            String wind = data.get(windKey);
            return Double.parseDouble(wind) * 1.609344;
        } catch (Exception e) {
            logWarning(windKey, data.get(windKey));
            return null;
        }
    }

    @Override
    protected Integer getWindDirection(Map<String, String> data) {
        try {
            return Integer.parseInt(data.get(windDirectionKey));
        } catch (Exception e) {
            logWarning(windDirectionKey, data.get(windDirectionKey));
            return null;
        }
    }

    @Override
    protected Double getPressure(Map<String, String> data) {
        try {
            String pressureInHg = data.get(pressureKey);
            return Double.parseDouble(pressureInHg) * 33.863889532610884;
        } catch (Exception e) {
            logWarning(pressureKey, data.get(pressureKey));
            return null;
        }
    }

    @Override
    protected Double getSolarRadiation(Map<String, String> data) {
        try {
            return Double.parseDouble(data.get(solarRadiationKey));
        } catch (Exception e) {
            logWarning(solarRadiationKey, data.get(solarRadiationKey));
            return null;
        }
    }

    private Double getCurrentRainCount(StationParameterEntity parameter) {
        try {
            return Double.parseDouble(parameter.getValue());
        } catch (Exception e) {
            logWarning(rainCountParameter, parameter.getValue());
        }
        return null;
    }

    private Double getNewRainCount(Map<String, String> data) {
        try {
            // get rainfallTotal of the new data
            String rain = data.get(rainTotalKey);
            return Double.parseDouble(rain) * 25.4;
        } catch (Exception e) {
            logWarning(rainTotalKey, data.get(rainTotalKey));
        }
        return null;
    }

    private StationParameterEntity getRainCountStationParameter(StationEntity station) {
        StationParameterEntity parameter = stationParameterRepository.findByStationAndCode(station, rainCountParameter);
        if (parameter == null) {
            parameter = StationParameterEntity.builder()
                    .station(station)
                    .code(rainCountParameter)
                    .value("0")
                    .build();
        }
        return parameter;
    }

    private void logWarning(String key, String value) {
        log.warn("Could not parse {} from string {}", key, value);
    }
}
