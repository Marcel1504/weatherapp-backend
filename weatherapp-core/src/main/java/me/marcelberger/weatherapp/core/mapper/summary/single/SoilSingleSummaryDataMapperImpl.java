package me.marcelberger.weatherapp.core.mapper.summary.single;

import me.marcelberger.weatherapp.core.data.summary.single.SoilSingleSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SoilSingleSummaryDataMapperImpl implements Mapper<SoilSingleSummaryEntity, SoilSingleSummaryData> {

    @Override
    public SoilSingleSummaryData map(SoilSingleSummaryEntity object) {
        return SoilSingleSummaryData.builder()
                .temperature50cm(object.getTemperature50cm())
                .temperature100cm(object.getTemperature100cm())
                .temperature200cm(object.getTemperature200cm())
                .timestamp(object.getTimestamp())
                .build();
    }
}
