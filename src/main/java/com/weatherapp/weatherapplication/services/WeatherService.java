package com.weatherapp.weatherapplication.services;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.models.Country;
import com.weatherapp.weatherapplication.models.Region;

public interface WeatherService {

    public double getTemperatureFromCity(WeatherApi weatherApi, City city);

    public double getAverageTemperatureFromRegion(WeatherApi weatherApi, Region region);

    public double getAverageTemperatureFromCountry(WeatherApi weatherApi, Country country);
}
