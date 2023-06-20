package com.weatherapp.weatherapplication.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class YandexApiTest {

    @Mock
    private YandexApi yandexApi;
    @Test
    void getWeatherDataFromApi() {
        Mockito.doReturn("Weather Data").when(yandexApi).getWeatherDataFromApi(anyDouble(),anyDouble());
        assertEquals("Weather Data", yandexApi.getWeatherDataFromApi(anyDouble(),anyDouble()));
        Mockito.verify(yandexApi).getWeatherDataFromApi(anyDouble(),anyDouble());
    }
    @Test
    void getIllegalArgumentExceptionOW() {
        Mockito.doThrow(IllegalArgumentException.class).when(yandexApi).getWeatherDataFromApi(9999,9999);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    yandexApi.getWeatherDataFromApi(9999,9999);
                });
    }

    @Test
    void getTemperature() {
        Mockito.doReturn(20d).when(yandexApi).getTemperature(anyString());
        assertEquals(20, yandexApi.getTemperature(anyString()));
        Mockito.verify(yandexApi).getTemperature(anyString());
    }
}