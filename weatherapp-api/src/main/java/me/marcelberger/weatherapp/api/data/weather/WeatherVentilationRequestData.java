package me.marcelberger.weatherapp.api.data.weather;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherVentilationRequestData {
    @NotNull(message = "{weather.ventilation.temperatureIn.null}")
    private Double temperatureIn;

    @NotNull(message = "{weather.ventilation.humidityIn.null}")
    @Max(value = 100, message = "{weather.ventilation.humidityIn.invalid}")
    @Min(value = 0, message = "{weather.ventilation.humidityIn.invalid}")
    private Integer humidityIn;

    @NotNull(message = "{weather.ventilation.temperatureOut.null}")
    private Double temperatureOut;

    @NotNull(message = "{weather.ventilation.humidityOut.null}")
    @Max(value = 100, message = "{weather.ventilation.humidityOut.invalid}")
    @Min(value = 0, message = "{weather.ventilation.humidityOut.invalid}")
    private Integer humidityOut;
}

