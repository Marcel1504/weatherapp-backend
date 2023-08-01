package me.marcelberger.weatherapp.api.dto.response.ventilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marcelberger.weatherapp.api.enumeration.VentilationIndicatorEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentilationResponseDto {
    private Double absoluteHumidityOut;
    private Double absoluteHumidityIn;
    private VentilationIndicatorEnum indicator;
}
