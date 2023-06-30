package com.weatherapp.weatherapplication.DB.repository;

import com.weatherapp.weatherapplication.DB.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region,Long> {
    Optional<Region> findByName(String region);

    List<Region> findAllByCountry_Id(Long country_id);
}
