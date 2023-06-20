package com.weatherapp.weatherapplication.services;

import com.weatherapp.weatherapplication.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OpenWeatherApiTest {
    @Mock
    private OpenWeatherApi openWeatherApi;
    @Test
    void getWeatherDataFromApi() {
        Mockito.doReturn("Weather Data").when(openWeatherApi).getWeatherDataFromApi(anyDouble(),anyDouble());
        assertEquals("Weather Data", openWeatherApi.getWeatherDataFromApi(anyDouble(),anyDouble()));
        Mockito.verify(openWeatherApi).getWeatherDataFromApi(anyDouble(),anyDouble());
    }
    @Test
    void getIllegalArgumentExceptionOW() {
        Mockito.doThrow(IllegalArgumentException.class).when(openWeatherApi).getWeatherDataFromApi(9999,9999);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    openWeatherApi.getWeatherDataFromApi(9999,9999);
                });
    }

    @Test
    void getTemperature() {
        Mockito.doReturn(20d).when(openWeatherApi).getTemperature(anyString());
        assertEquals(20, openWeatherApi.getTemperature(anyString()));
        Mockito.verify(openWeatherApi).getTemperature(anyString());
    }
}