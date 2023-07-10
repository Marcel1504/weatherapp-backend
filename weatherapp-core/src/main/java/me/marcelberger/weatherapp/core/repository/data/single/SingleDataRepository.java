package me.marcelberger.weatherapp.core.repository.data.single;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SingleDataRepository<ENTITY> {
    ENTITY findLatestByStationId(Long stationId);

    Page<ENTITY> findAllInTimestampRangeByStationId(Pageable pageable,
                                                    Long stationId,
                                                    String timestampStart,
                                                    String timestampEnd);

    ENTITY findFirstBeforeTimestampByStationId(Long stationId, String timestamp);

    ENTITY save(ENTITY s);
}
