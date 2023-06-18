package com.weatherapp.weatherapplication.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.weatherapplication.services.DataFromApiService;
import com.weatherapp.weatherapplication.services.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Override
    public double getTemperatureFromAllData(String weatherData) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(weatherData);
            //Double temperatureKel = node.get("main").get("temp").asDouble();
            double temperatureKel = node.get("fact").get("temp").asDouble();
            //Double temperatureCls = temperatureKel-273;
            double temperatureCls = temperatureKel;
            return temperatureCls;

        } catch (IOException e) {
            throw new RuntimeException("Error getting weather data", e);
        }
    }

}


