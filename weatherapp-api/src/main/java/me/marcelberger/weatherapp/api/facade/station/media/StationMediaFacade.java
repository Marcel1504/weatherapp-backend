package me.marcelberger.weatherapp.api.facade.station.media;

import me.marcelberger.weatherapp.api.dto.response.station.StationMediaFileResponseDto;

public interface StationMediaFacade {
    StationMediaFileResponseDto getStationMediaFile(String mediaName, String stationCode);
}
