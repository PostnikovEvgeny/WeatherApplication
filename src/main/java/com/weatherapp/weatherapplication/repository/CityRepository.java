package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String city);
}
