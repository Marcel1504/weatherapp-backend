package me.marcelberger.weatherapp.core.service.weather.wind;

import me.marcelberger.weatherapp.core.enumeration.WeatherWindDirectionEnum;
import org.springframework.stereotype.Service;

@Service
public class WeatherWindServiceImpl implements WeatherWindService {
    @Override
    public WeatherWindDirectionEnum degreeToWindDirectionEnum(Integer degree) {
        if (degree == null || degree < 0 || degree > 360) {
            return null;
        } else if (degree <= 22.5 && degree > 360 - 22.5) {
            return WeatherWindDirectionEnum.N;
        } else if (degree <= 67.5 && degree > 22.5) {
            return WeatherWindDirectionEnum.NE;
        } else if (degree <= 112.5 && degree > 67.5) {
            return WeatherWindDirectionEnum.E;
        } else if (degree <= 157.5 && degree > 112.5) {
            return WeatherWindDirectionEnum.SE;
        } else if (degree <= 202.5 && degree > 157.5) {
            return WeatherWindDirectionEnum.S;
        } else if (degree <= 247.5 && degree > 202.5) {
            return WeatherWindDirectionEnum.SW;
        } else if (degree <= 292.5 && degree > 247.5) {
            return WeatherWindDirectionEnum.W;
        } else {
            return WeatherWindDirectionEnum.NW;
        }
    }
}
