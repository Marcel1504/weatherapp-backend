package me.marcelberger.weatherapp.api.mapper.data.hour;

import me.marcelberger.weatherapp.api.dto.response.data.hour.WeatherHourDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.hour.WeatherHourDataEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherHourDataMapperImpl implements Mapper<WeatherHourDataEntity, WeatherHourDataResponseDto> {

    @Override
    public WeatherHourDataResponseDto map(WeatherHourDataEntity object) {
        return WeatherHourDataResponseDto.builder()
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
                .day(object.getDay())
                .hour(object.getHour())
                .build();
    }
}
