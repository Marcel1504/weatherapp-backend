package me.marcelberger.weatherapp.core.mapper.summary.hour;

import me.marcelberger.weatherapp.core.data.summary.hour.WeatherHourSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.hour.WeatherHourSummaryEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class WeatherHourSummaryDataMapperImpl implements Mapper<WeatherHourSummaryEntity, WeatherHourSummaryData> {

    @Override
    public WeatherHourSummaryData map(WeatherHourSummaryEntity object) {
        return WeatherHourSummaryData.builder()
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
                .day(object.getDay())
                .hour(object.getHour())
                .stationName(object.getStation() != null ? object.getStation().getName() : null)
                .build();
    }
}
