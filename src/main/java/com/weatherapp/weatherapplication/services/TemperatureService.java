package com.weatherapp.weatherapplication.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TemperatureService {

    @Value("${apiKey}")
    private String apiKey;

    public double getTemperature(double lat, double lon) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
            Map<String, String> params = new HashMap<>();
            params.put("lat", Double.toString(lat));
            params.put("lon", Double.toString(lon));
            params.put("apiKey", apiKey);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, params);
            String weatherData = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(weatherData);
            Double temperatureKel = node.get("main").get("temp").asDouble();
            Double temperatureCls = temperatureKel-273;

            return temperatureCls;

            } catch (IOException e) {
            throw new RuntimeException("Error getting weather data", e);
        }
    }
}


