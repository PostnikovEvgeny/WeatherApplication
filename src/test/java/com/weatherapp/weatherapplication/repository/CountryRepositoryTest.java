package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.models.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CountryRepositoryTest {
    @Autowired
    private CountryRepository countryRepository;
    @Test
    void findByName() {
        Country country = countryRepository.findByName("Russia");
        String name = "Russia";
        assertEquals(country.getName(), name);
    }
}