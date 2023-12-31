package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private CityRepository cityRepository;



    public double getTemperatureFromCity(WeatherApi weatherApi, String city) {
        City cityObj = cityRepository.findByName(city);
        double lat = cityObj.getLat();
        double lon = cityObj.getLon();
        String weatherData = weatherApi.getWeatherDataFromApi(lat,lon);
        double temperature = weatherApi.getTemperature(weatherData);
        return temperature;
    }
}
