package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.YandexApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class WeatherServiceImplTest {

    @Mock
    private WeatherServiceImpl weatherService;
    @Mock
    private OpenWeatherApi openWeather;
    @Mock
    private YandexApi yandexApi;

    @Test
    void gettingTempFromCityOW() {
        Mockito.doReturn(10d).when(weatherService).getTemperatureFromCity(openWeather,"Perm"); //еще можно так Mockito.when(weatherService.getTemperatureFromCity(openWeather,"Perm")).thenReturn(10d);
        assertEquals(10d, weatherService.getTemperatureFromCity(openWeather,"Perm"));
        Mockito.verify(weatherService).getTemperatureFromCity(openWeather,"Perm");
    }
    @Test
    void gettingTempFromIllegalCityOW() {
        Mockito.doThrow(IllegalArgumentException.class).when(weatherService).getTemperatureFromCity(openWeather,"123");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    weatherService.getTemperatureFromCity(openWeather,"123");
                });
    }

    @Test
    void gettingTempFromCityYandex() {
        Mockito.doReturn(10d).when(weatherService).getTemperatureFromCity(yandexApi,"Perm"); //еще можно так Mockito.when(weatherService.getTemperatureFromCity(yandexApi,"Perm")).thenReturn(10d);
        assertEquals(10d, weatherService.getTemperatureFromCity(yandexApi,"Perm"));
        Mockito.verify(weatherService).getTemperatureFromCity(yandexApi,"Perm");
    }
    @Test
    void gettingTempFromIllegalCityYandex() {
        Mockito.doThrow(IllegalArgumentException.class).when(weatherService).getTemperatureFromCity(yandexApi,"123");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    weatherService.getTemperatureFromCity(yandexApi,"123");
                });
    }
}