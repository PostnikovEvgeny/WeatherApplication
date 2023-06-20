package com.weatherapp.weatherapplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.weatherapplication.controllers.MainController;
import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.YandexApi;
import com.weatherapp.weatherapplication.services.impl.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherApplicationTests {

    @Autowired
    private MainController controller;
    @Autowired
    private OpenWeatherApi openWeatherApi;
    @Autowired
    private YandexApi yandexApi;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WeatherServiceImpl weatherServiceImpl;
    @Test
    void getTempTest()  throws Exception {
        String apiKey ="c49de9ec5f3294ceedfb67923fd8f026";
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
        Map<String, String> params = new HashMap<>();
        params.put("lat", Double.toString(58.0174));
        params.put("lon", Double.toString(56.2855));
        params.put("apiKey", apiKey);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, params);
        String weatherData = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(weatherData);
        Double temperatureKel = node.get("main").get("temp").asDouble();
        double expectedTemperature = temperatureKel-273;
        double actualTemperature = openWeatherApi.getTemperature(weatherData);
        assertEquals(expectedTemperature, actualTemperature);
    }
    @Test
    public void testNotInvalidCityOW() {
        double temperature = weatherServiceImpl.getTemperatureFromCity(openWeatherApi,"Perm");
        assertNotNull(temperature);
    }

    @Test
    public void testInvalidCityOW() {
        assertThrows(RuntimeException.class,
                () -> {
                    double temperature = weatherServiceImpl.getTemperatureFromCity(openWeatherApi,"123");
                });
    }
    @Test
    public void testNotInvalidCityYandex() {
        double temperature = weatherServiceImpl.getTemperatureFromCity(openWeatherApi,"Perm");
        assertNotNull(temperature);
    }

    @Test
    public void testInvalidCityYandex() {
        assertThrows(RuntimeException.class,
                () -> {
                    double temperature = weatherServiceImpl.getTemperatureFromCity(yandexApi,"123");
                });
    }







}
