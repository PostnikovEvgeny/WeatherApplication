package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.DB.models.Country;
import com.weatherapp.weatherapplication.DB.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CountryRepositoryTest {
    @Autowired
    private CountryRepository countryRepository;
    @Test
    void findByName() {
        Optional<Country> country = countryRepository.findByName("Russia");
        String name = "Russia";
        assertEquals(country.get().getName(), name);
    }
}