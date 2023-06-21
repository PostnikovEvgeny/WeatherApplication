package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.YandexApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class WeatherServiceImplTest {

    @InjectMocks
    private WeatherServiceImpl weatherService;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private OpenWeatherApi openWeather;
    @Mock
    private YandexApi yandexApi;

    @Test
    void gettingTempFromCityOW() {
        String city = "Perm";
        City cityObj = new City(1l,"Perm", 58.0174, 56.2855);
        when(cityRepository.findByName(city)).thenReturn(cityObj);
        String weatherData = "Weather data for Perm";
        when(openWeather.getWeatherDataFromApi(58.0174, 56.2855)).thenReturn(weatherData);
        double temperature = 10.5;
        when(openWeather.getTemperature(weatherData)).thenReturn(temperature);
        double result = weatherService.getTemperatureFromCity(openWeather, city);
        assertEquals(result, temperature, 0.1);
    }
    @Test
    void gettingTempFromIllegalCityOW() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    weatherService.getTemperatureFromCity(openWeather,"123");
                });
    }

    @Test
    void gettingTempFromCityYandex() {
        String city = "Perm";
        City cityObj = new City(1l,"Perm", 58.0174, 56.2855);
        when(cityRepository.findByName(city)).thenReturn(cityObj);
        String weatherData = "Weather data for Perm";
        when(yandexApi.getWeatherDataFromApi(58.0174, 56.2855)).thenReturn(weatherData);
        double temperature = 10.5;
        when(yandexApi.getTemperature(weatherData)).thenReturn(temperature);
        double result = weatherService.getTemperatureFromCity(yandexApi, city);
        assertEquals(result, temperature, 0.1);
    }
    @Test
    void gettingTempFromIllegalCityYandex() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    weatherService.getTemperatureFromCity(yandexApi,"123");
                });
    }
}