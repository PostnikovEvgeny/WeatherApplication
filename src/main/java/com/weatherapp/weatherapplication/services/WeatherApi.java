package com.weatherapp.weatherapplication.services;

import com.weatherapp.weatherapplication.models.City;
import org.springframework.stereotype.Service;

@Service
public interface WeatherApi {
    public String getWeatherDataFromApi(double lat, double lon);

    public double getTemperature(String weatherData);
}
