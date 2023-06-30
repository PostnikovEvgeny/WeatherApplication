package com.weatherapp.weatherapplication.DB.repository;

import com.weatherapp.weatherapplication.DB.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String city);
    List<City>findAllByRegion_Id(Long region_id);
}
