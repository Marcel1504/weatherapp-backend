package me.marcelberger.weatherapp.core.repository.data.hour;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HourDataRepository<ENTITY> {
    ENTITY findByStationAndDayAndHour(StationEntity station, String day, String hour);

    Page<ENTITY> findAllByStationAndDay(Pageable pageable, StationEntity station, String day);

    ENTITY save(ENTITY s);
}
