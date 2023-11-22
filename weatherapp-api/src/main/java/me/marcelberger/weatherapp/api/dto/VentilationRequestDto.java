package me.marcelberger.weatherapp.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentilationRequestDto {

    @NotNull(message = "{ventilation.temperatureIn.null}")
    private Double temperatureIn;

    @NotNull(message = "{ventilation.humidityIn.null}")
    @Max(value = 100, message = "{ventilation.humidityIn.invalid}")
    @Min(value = 0, message = "{ventilation.humidityIn.invalid}")
    private Integer humidityIn;

    @NotNull(message = "{ventilation.temperatureOut.null}")
    private Double temperatureOut;

    @NotNull(message = "{ventilation.humidityOut.null}")
    @Max(value = 100, message = "{ventilation.humidityOut.invalid}")
    @Min(value = 0, message = "{ventilation.humidityOut.invalid}")
    private Integer humidityOut;
}
