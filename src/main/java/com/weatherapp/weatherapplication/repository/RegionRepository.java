package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region,Long> {
    Region findByName(String region);

    List<Region> findAllByCountry_Id(Long country_id);
}
