package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.DB.models.City;
import com.weatherapp.weatherapplication.DB.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findByNameTest(){
        Optional<City> city = cityRepository.findByName("Perm");
        String name = "Perm";
        assertEquals(city.get().getName(), name);
    }

    @Test
    public void testInvalidCity(){
        Optional<City> city = cityRepository.findByName("123");
        assertTrue(city == null);
    }

}