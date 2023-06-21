package com.weatherapp.weatherapplication.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.weatherapplication.models.City;
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

public class YandexApi implements WeatherApi{

    @Autowired
    private RestTemplate restTemplate;

    public YandexApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${apiYandexKey}")
    String apiKey;
    @Override
    public String getWeatherDataFromApi(double lat, double lon) {
        String urlApi ="https://api.weather.yandex.ru/v2/forecast?lat={lat}&lon={lon}";
        URI url = UriComponentsBuilder.fromHttpUrl(urlApi).build(lat, lon);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Yandex-API-Key", apiKey);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        String weatherData = responseEntity.getBody();
        return weatherData;
    }
    public double getTemperature(String weatherData){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(weatherData);
            double temperatureCls = node.get("fact").get("temp").asDouble();
            return temperatureCls;

        } catch (IOException e) {
            throw new RuntimeException("Error getting temperature from YandexApi", e);
        }
    }
}
