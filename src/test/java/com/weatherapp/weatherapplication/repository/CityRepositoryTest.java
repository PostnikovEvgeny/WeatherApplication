package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.models.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityRepositoryTest {
    @Autowired
    private CityRepository cityRepository;
    @Test
    void findByNameTest() throws Exception{

        City city = cityRepository.findByName("Perm");
        assertThat(city.getName() == "Perm");
    }

    @Test
    public void testInvalidCity() throws Exception{
        City city = cityRepository.findByName("Perm");
        assertThat(city.getName() == null);
    }
}