package com.weatherapp.weatherapplication.services;

public interface WeatherApi {
    public String getWeatherDataFromApi(double lat, double lon);

    public double getTemperature(String weatherData);
}
