package com.weatherapp.weatherapplication.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OpenWeatherApiTest {
    @InjectMocks
    private OpenWeatherApi openWeatherApi;
    @Mock
    private RestTemplate restTemplate;
    @Test
    void getWeatherDataFromApi() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"main\": {\"temp\": 289.15}}", HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class), Mockito.any(Map.class)))
                .thenReturn(responseEntity);

        String weatherData = openWeatherApi.getWeatherDataFromApi(54.42, 18.57);
        assertEquals("{\"main\": {\"temp\": 289.15}}", weatherData);
    }
    @Test
    void getTemperature() {
        double temperature = openWeatherApi.getTemperature("{\"main\": {\"temp\": 283.0}}");
        assertEquals(10.0, temperature, 0.01);
    }
}