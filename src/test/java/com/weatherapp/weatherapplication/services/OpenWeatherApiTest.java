package com.weatherapp.weatherapplication.services;

import com.weatherapp.weatherapplication.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenWeatherApiTest {
    @Autowired
    private OpenWeatherApi openWeatherApi;

    @MockBean
    private CityRepository cityRepository;

    @Test
    void getWeatherDataFromApi() throws Exception{

    }

    @Test
    void getTemperature() throws Exception{
    }
}