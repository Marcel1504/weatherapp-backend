package me.marcelberger.weatherapp.api.controller.ventilation;

import jakarta.validation.Valid;
import me.marcelberger.weatherapp.api.dto.request.ventilation.VentilationRequestDto;
import me.marcelberger.weatherapp.api.dto.response.ventilation.VentilationResponseDto;
import me.marcelberger.weatherapp.api.facade.ventilation.VentilationFacade;
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
    public ResponseEntity<VentilationResponseDto> determineNeedForVentilation(
            @RequestBody @Valid VentilationRequestDto input) {
        return ResponseEntity.ok(ventilationFacade.determineNeedForVentilation(input));
    }
}
