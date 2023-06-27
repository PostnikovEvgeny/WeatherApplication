package com.weatherapp.weatherapplication.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class YandexApiTest {
    @InjectMocks
    private YandexApi yandexApi;
    @Mock
    private RestTemplate restTemplate;
    @Test
    void getWeatherDataFromApi() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"fact\": {\"temp\": 10.0}}", HttpStatus.OK);
        Mockito.when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        String weatherData = yandexApi.getWeatherDataFromApi(54.42, 18.57);
        assertEquals("{\"fact\": {\"temp\": 10.0}}", weatherData);
    }
    @Test
    void getTemperature() {
        double temperature = yandexApi.getTemperature("{\"fact\": {\"temp\": 10.0}}");
        assertEquals(10.0, temperature, 0.01);
    }
}