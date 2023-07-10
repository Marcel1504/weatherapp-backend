package me.marcelberger.weatherapp.api.mapper.data.day;

import me.marcelberger.weatherapp.api.dto.response.data.day.WeatherDayDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.day.WeatherDayDataEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherDayDataMapperImpl implements Mapper<WeatherDayDataEntity, WeatherDayDataResponseDto> {

    @Override
    public WeatherDayDataResponseDto map(WeatherDayDataEntity object) {
        return WeatherDayDataResponseDto.builder()
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
                .build();
    }
}
