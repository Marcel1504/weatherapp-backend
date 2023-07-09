package me.marcelberger.weatherapp.api.mapper.weather;

import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherYearEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherYearDataMapperImpl implements DataMapper<WeatherYearEntity, WeatherSummaryData> {
    @Override
    public WeatherSummaryData map(WeatherYearEntity object) {
        return WeatherSummaryData.builder()
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
                .pressureAvg(object.getPressureAvg())
                .pressureMax(object.getPressureMax())
                .pressureMin(object.getPressureMin())
                .solarRadiationAvg(object.getSolarRadiationAvg())
                .solarRadiationMax(object.getSolarRadiationMax())
                .solarRadiationMin(object.getSolarRadiationMin())
                .year(object.getYear())
                .build();
    }
}