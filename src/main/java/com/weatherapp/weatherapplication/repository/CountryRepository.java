package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.models.Country;
import org.hibernate.boot.model.internal.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByName(String country);
}
