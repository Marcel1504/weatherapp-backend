package me.marcelberger.weatherapp.api.mapper.data.year;

import me.marcelberger.weatherapp.api.dto.response.data.year.WeatherYearDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.year.WeatherYearDataEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherYearDataMapperImpl implements Mapper<WeatherYearDataEntity, WeatherYearDataResponseDto> {
    @Override
    public WeatherYearDataResponseDto map(WeatherYearDataEntity object) {
        return WeatherYearDataResponseDto.builder()
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
