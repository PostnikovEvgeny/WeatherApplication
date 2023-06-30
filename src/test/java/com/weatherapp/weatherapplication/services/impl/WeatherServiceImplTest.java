package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.DB.models.City;
import com.weatherapp.weatherapplication.DB.models.Region;
import com.weatherapp.weatherapplication.DB.repository.CityRepository;
import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.YandexApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
    @Mock
    private WeatherApi weatherApi;
    @Mock
    private Region region;



    @Test
    void gettingTempFromCityOW() {
        String city = "Perm";
        City cityObj = new City(6l,"Perm", 58.0174, 56.2855, region);
        String weatherData = "Weather data for Perm";
        when(openWeather.getWeatherDataFromApi(58.0174, 56.2855)).thenReturn(weatherData);
        double temperature = 10.5;
        when(cityRepository.findByName("Perm")).thenReturn(Optional.of(cityObj));
        when(openWeather.getTemperature(weatherData)).thenReturn(temperature);
        double result = weatherService.getTemperatureFromCity(openWeather, cityObj.getName());
        assertEquals(result, temperature, 0.1);
    }
    @Test
    void gettingTempFromIllegalCityOW() {
        Optional<City> city = null;
        assertThrows(NullPointerException.class,
                () -> {
                    weatherService.getTemperatureFromCity(openWeather,city.get().getName());
                });
    }

    @Test
    void gettingTempFromCityYandex() {
        String city = "Perm";
        City cityObj = new City(1l,"Perm", 58.0174, 56.2855,region);
        String weatherData = "Weather data for Perm";
        when(yandexApi.getWeatherDataFromApi(58.0174, 56.2855)).thenReturn(weatherData);
        double temperature = 10.5;
        when(cityRepository.findByName("Perm")).thenReturn(Optional.of(cityObj));
        when(yandexApi.getTemperature(weatherData)).thenReturn(temperature);
        double result = weatherService.getTemperatureFromCity(yandexApi, cityObj.getName());
        assertEquals(result, temperature, 0.1);
    }
    @Test
    void gettingTempFromIllegalCityYandex() {
        Optional<City> city = null;
        assertThrows(NullPointerException.class,
                () -> {
                    weatherService.getTemperatureFromCity(yandexApi,city.get().getName());
                });
    }

}