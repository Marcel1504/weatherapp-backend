package me.marcelberger.weatherapp.core.mapper.summary.year;

import me.marcelberger.weatherapp.core.data.summary.year.WeatherYearSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class WeatherYearSummaryDataMapperImpl implements Mapper<WeatherYearSummaryEntity, WeatherYearSummaryData> {
    @Override
    public WeatherYearSummaryData map(WeatherYearSummaryEntity object) {
        return WeatherYearSummaryData.builder()
                .amount(object.getAmount())
                .temperatureAvg(object.getTemperatureAvg())
                .temperatureMax(object.getTemperatureMax())
                .temperatureMin(object.getTemperatureMin())
                .humidityAvg(object.getHumidityAvg())
                .humidityMax(object.getHumidityMax())
                .humidityMin(object.getHumidityMin())
                .rainTotal(object.getRainTotal())
                .rainRateMax(object.getRainRateMax())
                .windMax(object.getWindMax())
                .windDirectionCluster(object.getWindDirectionCluster())
                .pressureAvg(object.getPressureAvg())
                .pressureMax(object.getPressureMax())
                .pressureMin(object.getPressureMin())
                .solarRadiationAvg(object.getSolarRadiationAvg())
                .solarRadiationMax(object.getSolarRadiationMax())
                .solarRadiationMin(object.getSolarRadiationMin())
                .year(object.getYear())
                .stationName(object.getStation() != null ? object.getStation().getName() : null)
                .build();
    }
}
