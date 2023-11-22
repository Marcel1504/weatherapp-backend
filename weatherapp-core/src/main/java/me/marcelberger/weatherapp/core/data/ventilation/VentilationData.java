package me.marcelberger.weatherapp.core.data.ventilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marcelberger.weatherapp.core.enumeration.ventilation.VentilationIndicatorEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentilationData {
    private Double absoluteHumidityOut;
    private Double absoluteHumidityIn;
    private VentilationIndicatorEnum indicator;
}
