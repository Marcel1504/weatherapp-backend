package me.marcelberger.weatherapp.api.mapper.data.month;

import me.marcelberger.weatherapp.api.dto.response.data.month.WeatherMonthDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.month.WeatherMonthDataEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherMonthDataMapperImpl implements Mapper<WeatherMonthDataEntity, WeatherMonthDataResponseDto> {

    @Override
    public WeatherMonthDataResponseDto map(WeatherMonthDataEntity object) {
        return WeatherMonthDataResponseDto.builder()
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
