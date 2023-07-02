package me.marcelberger.weatherapp.core.repository;

import me.marcelberger.weatherapp.core.entity.StationMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationMediaRepository extends JpaRepository<StationMediaEntity, Long> {

    StationMediaEntity findByName(String name);

    boolean existsByName(String name);
}
