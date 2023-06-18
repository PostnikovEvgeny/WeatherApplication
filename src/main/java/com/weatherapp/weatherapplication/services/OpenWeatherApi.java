package com.weatherapp.weatherapplication.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeatherApi implements WeatherApi{
    @Autowired
    private RestTemplate restTemplate;

    //@Value("${apiOpenWeatherKey}")
    //String apiKey = "c49de9ec5f3294ceedfb67923fd8f026";

    //public static String urlApi ="https://api.weather.yandex.ru/v2/forecast?lat={lat}&lon={lon}";
    //String urlApi = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
    @Override
    public String getWeatherDataFromApi(double lat, double lon) {
        String apiKey = "c49de9ec5f3294ceedfb67923fd8f026";
        String urlApi = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
        Map<String, String> params = new HashMap<>();
        params.put("lat", Double.toString(lat));
        params.put("lon", Double.toString(lon));
        params.put("apiKey", apiKey);
        RestTemplate restTemplate1 = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate1.getForEntity(urlApi, String.class, params);
        String weatherData = responseEntity.getBody();
        System.out.println(weatherData);
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
