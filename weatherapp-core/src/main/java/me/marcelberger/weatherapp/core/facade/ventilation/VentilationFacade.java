package me.marcelberger.weatherapp.core.facade.ventilation;

import me.marcelberger.weatherapp.core.data.ventilation.VentilationData;

public interface VentilationFacade {
    VentilationData determineNeedForVentilation(Double temperatureIn,
                                                Integer humidityIn,
                                                Double temperatureOut,
                                                Integer humidityOut);
}
