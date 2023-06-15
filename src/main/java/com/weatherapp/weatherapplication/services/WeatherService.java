package com.weatherapp.weatherapplication.services;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TemperatureService temperatureService;


    public double getTemperature(String city) {
        City cityObj = cityRepository.findByName(city);
        double lat = cityObj.getLat();
        double lon = cityObj.getLon();
        double temperature = temperatureService.getTemperature(lat, lon);
        return temperature;
    }
}
