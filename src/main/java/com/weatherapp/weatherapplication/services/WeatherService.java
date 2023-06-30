package com.weatherapp.weatherapplication.services;

public interface WeatherService {

    public double getTemperatureFromCity(WeatherApi weatherApi, String city);

    public double getAverageTemperatureFromRegion(WeatherApi weatherApi, String region);

    public double getAverageTemperatureFromCountry(WeatherApi weatherApi, String country);
}
