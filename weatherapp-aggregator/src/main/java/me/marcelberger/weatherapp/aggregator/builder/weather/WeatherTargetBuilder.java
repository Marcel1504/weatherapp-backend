package me.marcelberger.weatherapp.aggregator.builder.weather;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.core.entity.data.WeatherSummaryDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.enumeration.WeatherWindDirectionEnum;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class WeatherTargetBuilder<T extends WeatherSummaryDataEntity> implements TargetBuilder<WeatherDataEntity, T> {

    private final WeatherWindService weatherWindService;
    private final Map<WeatherWindDirectionEnum, Integer> windDirectionCountMap = new HashMap<>();
    private BigDecimal temperatureAvgBase = new BigDecimal("0.0");
    private Integer temperatureCount = 0;
    private Double temperatureMax;
    private Double temperatureMin;
    private BigDecimal humidityAvgBase = new BigDecimal("0.0");
    private Integer humidityCount = 0;
    private Integer humidityMin;
    private Integer humidityMax;
    private BigDecimal pressureAvgBase = new BigDecimal("0.0");
    private Integer pressureCount = 0;
    private Double pressureMin;
    private Double pressureMax;
    private BigDecimal solarRadiationAvgBase = new BigDecimal("0.0");
    private Integer solarRadiationCount = 0;
    private Double solarRadiationMin;
    private Double solarRadiationMax;
    private Double rainTotal = 0.0;
    private Double rainRateMax = 0.0;
    private Double windMax = 0.0;
    private Integer amount = 0;

    public WeatherTargetBuilder(WeatherWindService weatherWindService) {
        this.weatherWindService = weatherWindService;
    }

    @Override
    public void pushSourceEntities(List<WeatherDataEntity> entities) {
        entities.forEach(w -> {
            pushTemperature(w.getTemperature());
            pushHumidity(w.getHumidity());
            pushPressure(w.getPressure());
            pushSolarRadiation(w.getSolarRadiation());
            pushRainDelta(w.getRainDelta());
            pushRainRate(w.getRainRate());
            pushWind(w.getWind());
            pushWindDirection(w.getWindDirection());
            amount++;
        });
    }

    @Override
    public T build(T base) {
        base.setAmount(amount);
        if (amount != 0) {
            base.setTemperatureAvg(getAverage(temperatureCount, temperatureAvgBase));
            base.setTemperatureMax(temperatureMax);
            base.setTemperatureMin(temperatureMin);
            base.setHumidityAvg(getAverage(humidityCount, humidityAvgBase));
            base.setHumidityMin(humidityMin);
            base.setHumidityMax(humidityMax);
            base.setPressureAvg(getAverage(pressureCount, pressureAvgBase));
            base.setPressureMin(pressureMin);
            base.setPressureMax(pressureMax);
            base.setSolarRadiationAvg(getAverage(solarRadiationCount, solarRadiationAvgBase));
            base.setSolarRadiationMin(solarRadiationMin);
            base.setSolarRadiationMax(solarRadiationMax);
            base.setRainRateMax(rainRateMax);
            base.setRainTotal(rainTotal);
            base.setWindMax(windMax);
            base.setWindDirectionCluster(getWindDirectionCluster());
        }
        return base;
    }

    @Override
    public boolean hasContent() {
        return amount > 0;
    }

    private void pushTemperature(Double temperature) {
        if (temperature != null) {
            temperatureAvgBase = temperatureAvgBase.add(BigDecimal.valueOf(temperature));
            temperatureMax = temperatureMax == null ? temperature : temperatureMax;
            temperatureMax = temperature > temperatureMax ? temperature : temperatureMax;
            temperatureMin = temperatureMin == null ? temperature : temperatureMin;
            temperatureMin = temperature < temperatureMin ? temperature : temperatureMin;
            temperatureCount++;
        }
    }

    private void pushHumidity(Integer humidity) {
        if (humidity != null) {
            humidityAvgBase = humidityAvgBase.add(BigDecimal.valueOf(humidity));
            humidityMax = humidityMax == null ? humidity : humidityMax;
            humidityMax = humidity > humidityMax ? humidity : humidityMax;
            humidityMin = humidityMin == null ? humidity : humidityMin;
            humidityMin = humidity < humidityMin ? humidity : humidityMin;
            humidityCount++;
        }
    }

    private void pushPressure(Double pressure) {
        if (pressure != null) {
            pressureAvgBase = pressureAvgBase.add(BigDecimal.valueOf(pressure));
            pressureMax = pressureMax == null ? pressure : pressureMax;
            pressureMax = pressure > pressureMax ? pressure : pressureMax;
            pressureMin = pressureMin == null ? pressure : pressureMin;
            pressureMin = pressure < pressureMin ? pressure : pressureMin;
            pressureCount++;
        }
    }

    private void pushSolarRadiation(Double solarRadiation) {
        if (solarRadiation != null) {
            solarRadiationAvgBase = solarRadiationAvgBase.add(BigDecimal.valueOf(solarRadiation));
            solarRadiationMax = solarRadiationMax == null ? solarRadiation : solarRadiationMax;
            solarRadiationMax = solarRadiation > solarRadiationMax ? solarRadiation : solarRadiationMax;
            solarRadiationMin = solarRadiationMin == null ? solarRadiation : solarRadiationMin;
            solarRadiationMin = solarRadiation < solarRadiationMin ? solarRadiation : solarRadiationMin;
            solarRadiationCount++;
        }
    }

    private void pushWindDirection(Integer windDirection) {
        WeatherWindDirectionEnum direction = weatherWindService.degreeToWindDirectionEnum(windDirection);
        if (direction == null) return;
        if (!windDirectionCountMap.containsKey(direction)) {
            windDirectionCountMap.put(direction, 1);
        } else {
            windDirectionCountMap.put(direction, windDirectionCountMap.get(direction) + 1);
        }
    }

    private void pushWind(Double wind) {
        if (wind != null) {
            windMax = wind > windMax ? wind : windMax;
        }
    }

    private void pushRainDelta(Double rainDelta) {
        if (rainDelta != null) {
            rainTotal += rainDelta;
        }
    }

    private void pushRainRate(Double rainRate) {
        if (rainRate != null) {
            rainRateMax = rainRate > rainRateMax ? rainRate : rainRateMax;
        }
    }

    private Double getAverage(Integer count, BigDecimal base) {
        return count > 0
                ? base
                .divide(BigDecimal.valueOf(count), RoundingMode.HALF_UP)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue()
                : null;
    }

    private WeatherWindDirectionEnum getWindDirectionCluster() {
        WeatherWindDirectionEnum mostCommonCluster = null;
        Integer currentClusterCountMax = 0;
        for (Map.Entry<WeatherWindDirectionEnum, Integer> entry : windDirectionCountMap.entrySet()) {
            if (entry.getValue() > currentClusterCountMax) {
                currentClusterCountMax = entry.getValue();
                mostCommonCluster = entry.getKey();
            }
        }
        return mostCommonCluster;
    }
}
