package me.marcelberger.weatherapp.core.facade.ventilation;

import me.marcelberger.weatherapp.core.data.ventilation.VentilationData;
import me.marcelberger.weatherapp.core.enumeration.ventilation.VentilationIndicatorEnum;
import me.marcelberger.weatherapp.core.service.math.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VentilationFacadeImpl implements VentilationFacade {

    @Autowired
    private MathService mathService;


    @Override
    public VentilationData determineNeedForVentilation(Double temperatureIn,
                                                       Integer humidityIn,
                                                       Double temperatureOut,
                                                       Integer humidityOut) {
        Double absoluteHumidityIn = calculateAbsoluteHumidity(temperatureIn, humidityIn);
        Double absoluteHumidityOut = calculateAbsoluteHumidity(temperatureOut, humidityOut);
        VentilationIndicatorEnum indicator = determineVentilationIndicator(
                absoluteHumidityIn,
                absoluteHumidityOut,
                temperatureIn,
                temperatureOut);
        return VentilationData.builder()
                .absoluteHumidityIn(absoluteHumidityIn)
                .absoluteHumidityOut(absoluteHumidityOut)
                .indicator(indicator)
                .build();
    }

    private Double calculateSaturatedVaporPressure(Double temperature) {
        if (temperature < 0) {
            return 610.5 * Math.exp((21.875 * temperature) / (265.5 + temperature));
        } else {
            return 610.5 * Math.exp((17.269 * temperature) / (237.3 + temperature));
        }
    }

    private Double calculateVaporPartialPressure(Double temperature, Integer humidity) {
        return ((double) humidity * calculateSaturatedVaporPressure(temperature)) / 100;
    }

    private Double calculateAbsoluteHumidity(Double temperature, Integer humidity) {
        return mathService.round(
                calculateVaporPartialPressure(temperature, humidity) / (461.5 * (temperature + 273)) * 1000,
                1);
    }

    private VentilationIndicatorEnum determineVentilationIndicator(Double absoluteHumidityIn,
                                                                   Double absoluteHumidityOut,
                                                                   Double temperatureIn,
                                                                   Double temperatureOut) {
        if (absoluteHumidityOut < absoluteHumidityIn && temperatureOut > temperatureIn) {
            return VentilationIndicatorEnum.GREEN;
        }
        if (absoluteHumidityOut < absoluteHumidityIn) {
            return VentilationIndicatorEnum.YELLOW1;
        }
        if (Objects.equals(absoluteHumidityIn, absoluteHumidityOut) && temperatureOut > temperatureIn) {
            return VentilationIndicatorEnum.YELLOW2;
        }
        return VentilationIndicatorEnum.RED;
    }
}
