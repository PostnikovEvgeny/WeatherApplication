package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class WeatherServiceImplTest {

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private WeatherServiceImpl weatherService;
    @MockBean
    private WeatherApi weatherApi;

    /*public WeatherServiceImplTest(){
        this.weatherService = new WeatherServiceImpl();
    }*/
    @Test
    void gettingTempFromCityOW() {
        //WeatherApi weatherApi = mock(OpenWeatherApi.class);
        double temp = weatherService.getTemperatureFromCity(weatherApi,"Perm");
        assertEquals(temp,weatherService.getTemperatureFromCity(weatherApi,"Perm"));
    }
}