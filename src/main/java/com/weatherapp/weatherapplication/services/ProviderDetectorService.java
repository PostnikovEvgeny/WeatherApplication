package com.weatherapp.weatherapplication.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderDetectorService {

    @Autowired
    private WeatherApi weatherApi;
    @Autowired
    private YandexApi yandexApi;
    @Autowired
    private OpenWeatherApi openWeatherApi;

    public WeatherApi DetectTheProvider(String provider) {
        switch (provider)
        {
            case "OpenWeather":
            {
                weatherApi = openWeatherApi;
                break;
            }
            case "Yandex":
            {
                weatherApi = yandexApi;
                break;
            }
            default:
                return weatherApi;
        }
        return weatherApi;
    }
}
