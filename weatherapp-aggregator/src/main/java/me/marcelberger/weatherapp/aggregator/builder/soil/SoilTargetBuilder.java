package me.marcelberger.weatherapp.aggregator.builder.soil;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.core.entity.data.SoilSummaryDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public abstract class SoilTargetBuilder<T extends SoilSummaryDataEntity> implements TargetBuilder<SoilSingleDataEntity, T> {

    private BigDecimal temperature50cmAvgBase = new BigDecimal("0.0");
    private Integer temperature50cmCount = 0;
    private Double temperature50cmMax;
    private Double temperature50cmMin;
    private BigDecimal temperature100cmAvgBase = new BigDecimal("0.0");
    private Integer temperature100cmCount = 0;
    private Double temperature100cmMax;
    private Double temperature100cmMin;
    private BigDecimal temperature200cmAvgBase = new BigDecimal("0.0");
    private Integer temperature200cmCount = 0;
    private Double temperature200cmMax;
    private Double temperature200cmMin;
    private Integer amount = 0;

    @Override
    public void pushSourceEntities(List<SoilSingleDataEntity> entities) {
        entities.forEach(s -> {
            pushTemperature50cm(s.getTemperature50cm());
            pushTemperature100cm(s.getTemperature100cm());
            pushTemperature200cm(s.getTemperature200cm());
            amount++;
        });
    }

    @Override
    public T build(T base) {
        base.setAmount(amount);
        if (amount != 0) {
            base.setTemperature50cmAvg(getAverage(temperature50cmCount, temperature50cmAvgBase));
            base.setTemperature50cmMax(temperature50cmMax);
            base.setTemperature50cmMin(temperature50cmMin);
            base.setTemperature100cmAvg(getAverage(temperature100cmCount, temperature100cmAvgBase));
            base.setTemperature100cmMax(temperature100cmMax);
            base.setTemperature100cmMin(temperature100cmMin);
            base.setTemperature200cmAvg(getAverage(temperature200cmCount, temperature200cmAvgBase));
            base.setTemperature200cmMax(temperature200cmMax);
            base.setTemperature200cmMin(temperature200cmMin);
        }
        return base;
    }

    @Override
    public boolean hasContent() {
        return amount > 0;
    }

    private void pushTemperature50cm(Double temperature) {
        if (temperature != null) {
            temperature50cmAvgBase = temperature50cmAvgBase.add(BigDecimal.valueOf(temperature));
            temperature50cmMax = temperature50cmMax == null ? temperature : temperature50cmMax;
            temperature50cmMax = temperature > temperature50cmMax ? temperature : temperature50cmMax;
            temperature50cmMin = temperature50cmMin == null ? temperature : temperature50cmMin;
            temperature50cmMin = temperature < temperature50cmMin ? temperature : temperature50cmMin;
            temperature50cmCount++;
        }
    }

    private void pushTemperature100cm(Double temperature) {
        if (temperature != null) {
            temperature100cmAvgBase = temperature100cmAvgBase.add(BigDecimal.valueOf(temperature));
            temperature100cmMax = temperature100cmMax == null ? temperature : temperature100cmMax;
            temperature100cmMax = temperature > temperature100cmMax ? temperature : temperature100cmMax;
            temperature100cmMin = temperature100cmMin == null ? temperature : temperature100cmMin;
            temperature100cmMin = temperature < temperature100cmMin ? temperature : temperature100cmMin;
            temperature100cmCount++;
        }
    }

    private void pushTemperature200cm(Double temperature) {
        if (temperature != null) {
            temperature200cmAvgBase = temperature200cmAvgBase.add(BigDecimal.valueOf(temperature));
            temperature200cmMax = temperature200cmMax == null ? temperature : temperature200cmMax;
            temperature200cmMax = temperature > temperature200cmMax ? temperature : temperature200cmMax;
            temperature200cmMin = temperature200cmMin == null ? temperature : temperature200cmMin;
            temperature200cmMin = temperature < temperature200cmMin ? temperature : temperature200cmMin;
            temperature200cmCount++;
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
}
