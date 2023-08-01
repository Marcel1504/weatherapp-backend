package me.marcelberger.weatherapp.api.facade.ventilation;

import me.marcelberger.weatherapp.api.dto.request.ventilation.VentilationRequestDto;
import me.marcelberger.weatherapp.api.dto.response.ventilation.VentilationResponseDto;

public interface VentilationFacade {
    VentilationResponseDto determineNeedForVentilation(VentilationRequestDto input);
}
