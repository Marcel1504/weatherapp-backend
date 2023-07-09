package me.marcelberger.weatherapp.api.dto.response.data.single;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.api.dto.response.data.DataResponseDto;

import java.time.LocalDateTime;

@Data
@Builder
public class SoilSingleDataResponseDto implements DataResponseDto {
    private Double temperature50cm;
    private Double temperature100cm;
    private Double temperature200cm;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
