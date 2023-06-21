package com.weatherapp.weatherapplication.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OpenWeatherApi implements WeatherApi{

    @Autowired
    private RestTemplate restTemplate;

    @Value("${apiOpenWeatherKey}")
    private String apiKey;
    public OpenWeatherApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getWeatherDataFromApi(double lat, double lon) {
        String urlApi = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
        Map<String, String> params = new HashMap<>();
        params.put("lat", Double.toString(lat));
        params.put("lon", Double.toString(lon));
        params.put("apiKey", apiKey);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlApi, String.class, params);
        String weatherData = responseEntity.getBody();
        return weatherData;
    }

    @Override
    public double getTemperature(String weatherData) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(weatherData);
            Double temperatureKel = node.get("main").get("temp").asDouble();
            Double temperatureCls = temperatureKel-273;
            return temperatureCls;

        } catch (IOException e) {
            throw new RuntimeException("Error getting temperature from OpenWeatherApi", e);
        }
    }

}
