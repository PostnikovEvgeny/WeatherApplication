package com.weatherapp.weatherapplication.services;

import com.weatherapp.weatherapplication.models.City;

public interface WeatherService {

    public double getTemperatureFromCity(WeatherApi weatherApi,String city); //надо передать объект OW или Yand
}
