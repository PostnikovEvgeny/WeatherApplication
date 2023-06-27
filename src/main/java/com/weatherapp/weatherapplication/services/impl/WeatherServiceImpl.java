package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.models.Country;
import com.weatherapp.weatherapplication.models.Region;
import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.repository.CountryRepository;
import com.weatherapp.weatherapplication.repository.RegionRepository;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CountryRepository countryRepository;

    public double getTemperatureFromCity(WeatherApi weatherApi, City city) {
        if(city != null) {
            double lat = city.getLat();
            double lon = city.getLon();
            String weatherData = weatherApi.getWeatherDataFromApi(lat, lon);
            double temperature = weatherApi.getTemperature(weatherData);
            return temperature;
        }
        else {
            throw new IllegalArgumentException("There is no such city in DB");
        }
    }

    @Override
    public double getAverageTemperatureFromRegion(WeatherApi weatherApi, Region region) {
        if(region != null) {
            List<City> cities = cityRepository.findAllByRegion_Id(region.getId());
            double totalTemp=0.0;
            for (var city:cities) {
                totalTemp+=getTemperatureFromCity(weatherApi,city);
            }
            double AvgTemp = totalTemp / cities.size();
            return AvgTemp;
        }
        else {
            throw new IllegalArgumentException("There is no such region in DB");
        }
    }

    @Override
    public double getAverageTemperatureFromCountry(WeatherApi weatherApi, Country country) {
        if(country != null) {
            List<Region> regions = regionRepository.findAllByCountry_Id(country.getId());
            double totalTemp=0.0;
            for (var region:regions) {
                totalTemp+=getAverageTemperatureFromRegion(weatherApi,region);
            }
            double AvgTemp = totalTemp / regions.size();
            return AvgTemp;
        }
        else {
            throw new IllegalArgumentException("There is no such country in DB");
        }
    }


}
