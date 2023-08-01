package me.marcelberger.weatherapp.api.facade.ventilation;

import me.marcelberger.weatherapp.api.dto.request.ventilation.VentilationRequestDto;
import me.marcelberger.weatherapp.api.dto.response.ventilation.VentilationResponseDto;
import me.marcelberger.weatherapp.api.enumeration.VentilationIndicatorEnum;
import me.marcelberger.weatherapp.core.service.math.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VentilationFacadeImpl implements VentilationFacade {

    @Autowired
    private MathService mathService;

    @Override
    public VentilationResponseDto determineNeedForVentilation(VentilationRequestDto input) {
        Double absoluteHumidityIn = calculateAbsoluteHumidity(input.getTemperatureIn(), input.getHumidityIn());
        Double absoluteHumidityOut = calculateAbsoluteHumidity(input.getTemperatureOut(), input.getHumidityOut());
        VentilationIndicatorEnum indicator = determineVentilationIndicator(
                absoluteHumidityIn,
                absoluteHumidityOut,
                input.getTemperatureIn(),
                input.getTemperatureOut());
        return VentilationResponseDto.builder()
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
