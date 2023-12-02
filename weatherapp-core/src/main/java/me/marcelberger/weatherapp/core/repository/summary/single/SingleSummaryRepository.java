package me.marcelberger.weatherapp.core.repository.summary.single;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SingleSummaryRepository<ENTITY> {
    ENTITY findLatestByStationId(Long stationId);

    Page<ENTITY> findAllInTimestampRangeByStationId(Pageable pageable,
                                                    Long stationId,
                                                    String timestampStart,
                                                    String timestampEnd);

    ENTITY save(ENTITY s);
}
