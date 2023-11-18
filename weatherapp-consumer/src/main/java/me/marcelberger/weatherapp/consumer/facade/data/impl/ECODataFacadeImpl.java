package me.marcelberger.weatherapp.consumer.facade.data.impl;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.consumer.facade.data.DataFacade;
import me.marcelberger.weatherapp.consumer.service.data.updater.impl.ECODataUpdaterServiceImpl;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ECODataFacadeImpl extends DataFacade<String> {

    @Value("${weatherapp.station.vendor.ecowitt.passkey}")
    private String passkeyKey;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private ECODataUpdaterServiceImpl dataUpdaterService;

    @Override
    protected StationEntity getStationFromData(String data) {
        StationEntity station;
        if (data != null) {
            String passkey = getDataMapFromString(data).get(passkeyKey);
            station = stationRepository.findByApiKey(passkey);
            if (station == null) {
                throw new ServiceException("No station with API key '%s' found", passkey);
            }
            return station;
        }
        throw new ServiceException("Cannot load station from ECO data: No data provided");
    }

    @Override
    protected void update(StationEntity station, String data) {
        log.debug("ECOWITT message for {}: {}", station.getCode(), data);
        dataUpdaterService.update(station, getDataMapFromString(data));
    }

    @Override
    protected StationTypeEnum stationType() {
        return StationTypeEnum.WEATHER;
    }

    private Map<String, String> getDataMapFromString(String data) {
        return Arrays.stream(data.split("&"))
                .map(kv -> kv.split("="))
                .collect(Collectors.toMap((v) -> v[0], (v) -> URLDecoder.decode(v[1], StandardCharsets.UTF_8)));
    }
}