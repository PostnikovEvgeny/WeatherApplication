package com.weatherapp.weatherapplication.services;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TemperatureService {

    private String apiKey = "c49de9ec5f3294ceedfb67923fd8f026";

    public String getTemperature(double lat, double lon) {
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

            return temperatureCls.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


