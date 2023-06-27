package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String city);
    List<City>findAllByRegion_Id(Long region_id);
}
