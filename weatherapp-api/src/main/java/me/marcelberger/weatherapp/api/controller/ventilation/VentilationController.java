package me.marcelberger.weatherapp.api.controller.ventilation;

import jakarta.validation.Valid;
import me.marcelberger.weatherapp.api.dto.VentilationRequestDto;
import me.marcelberger.weatherapp.core.data.ventilation.VentilationData;
import me.marcelberger.weatherapp.core.facade.ventilation.VentilationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventilation")
@Validated
@CrossOrigin
public class VentilationController {

    @Autowired
    private VentilationFacade ventilationFacade;

    @PutMapping
    public ResponseEntity<VentilationData> determineNeedForVentilation(
            @RequestBody @Valid VentilationRequestDto input) {
        return ResponseEntity.ok(ventilationFacade.determineNeedForVentilation(
                input.getTemperatureIn(),
                input.getHumidityIn(),
                input.getTemperatureOut(),
                input.getHumidityOut()));
    }
}
