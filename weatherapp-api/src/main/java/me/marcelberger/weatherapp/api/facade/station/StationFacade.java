package me.marcelberger.weatherapp.api.facade.station;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.station.StationResponseDto;

public interface StationFacade {
    PageData<StationResponseDto> getAll();

    StationResponseDto getByStationCode(String stationCode);
}
