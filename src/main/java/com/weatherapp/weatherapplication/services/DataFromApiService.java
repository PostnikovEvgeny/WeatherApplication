package com.weatherapp.weatherapplication.services;

public interface DataFromApiService {

    public String getDataFromApi(String apiKey, double lat, double lon);
}
