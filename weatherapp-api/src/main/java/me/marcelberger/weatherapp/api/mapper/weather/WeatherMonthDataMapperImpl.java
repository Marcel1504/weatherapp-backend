package me.marcelberger.weatherapp.api.mapper.weather;

import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherMonthEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherMonthDataMapperImpl implements DataMapper<WeatherMonthEntity, WeatherSummaryData> {

    @Override
    public WeatherSummaryData map(WeatherMonthEntity object) {
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
                .month(object.getMonth())
                .year(object.getYear())
                .build();
    }
}
