package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.YandexApi;
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

/*
    @MockBean
    private CityRepository cityRepository;
*/

    @MockBean
    private WeatherServiceImpl weatherService;
   // @MockBean
    @Mock
    private OpenWeatherApi openWeather;
    @Mock
    private YandexApi yandexApi;

    /*public WeatherServiceImplTest(){
        this.weatherService = new WeatherServiceImpl();
    }*/
    @Test
    void gettingTempFromCityOW() {
        double temp = weatherService.getTemperatureFromCity(openWeather,"Perm");
       //assertEquals(temp,weatherService.getTemperatureFromCity(openWeather,"Perm"));
        Mockito.when(weatherService.getTemperatureFromCity(openWeather,"Perm")).thenReturn(temp);
    }
    @Test
    void gettingTempFromCityYandex() {
        double temp = weatherService.getTemperatureFromCity(yandexApi,"Perm");
        assertEquals(temp,weatherService.getTemperatureFromCity(yandexApi,"Perm"));
    }
}