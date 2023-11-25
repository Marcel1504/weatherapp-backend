package me.marcelberger.weatherapp.consumer.facade.data.impl;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.consumer.facade.data.DataFacade;
import me.marcelberger.weatherapp.consumer.service.data.updater.impl.ECODataUpdaterServiceImpl;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.station.StationTypeEnum;
import me.marcelberger.weatherapp.core.service.station.StationService;
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
    private StationService stationService;

    @Autowired
    private ECODataUpdaterServiceImpl dataUpdaterService;

    @Override
    protected StationEntity getStationFromDataOrNull(String data) {
        if (data != null) {
            String passkey = getDataMapFromString(data).get(passkeyKey);
            return stationService.getByAPIKey(passkey);
        }
        return null;
    }

    @Override
    protected void update(StationEntity station, String data) {
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
