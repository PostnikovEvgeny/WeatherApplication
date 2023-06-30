package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.DB.models.City;
import com.weatherapp.weatherapplication.DB.models.Country;
import com.weatherapp.weatherapplication.DB.models.Region;
import com.weatherapp.weatherapplication.DB.repository.CityRepository;
import com.weatherapp.weatherapplication.DB.repository.CountryRepository;
import com.weatherapp.weatherapplication.DB.repository.RegionRepository;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CountryRepository countryRepository;

    public double getTemperatureFromCity(WeatherApi weatherApi, String city) {
        Optional<City> cityObj = cityRepository.findByName(city);
        if(cityObj.isPresent()) {
            double lat = cityObj.get().getLat();
            double lon = cityObj.get().getLon();
            String weatherData = weatherApi.getWeatherDataFromApi(lat, lon);
            double temperature = weatherApi.getTemperature(weatherData);
            return temperature;
        }
        else {
            throw new IllegalArgumentException("There is no such city in DB");
        }
    }

    @Override
    public double getAverageTemperatureFromRegion(WeatherApi weatherApi, String region) {
        Optional<Region> regionObj = regionRepository.findByName(region);
        if(regionObj.isPresent()) {
            List<City> cities = cityRepository.findAllByRegion_Id(regionObj.get().getId());
            double totalTemp=0.0;
            for (var city:cities) {
                totalTemp+=getTemperatureFromCity(weatherApi,city.getName());
            }
            double AvgTemp = totalTemp / cities.size();
            return AvgTemp;
        }
        else {
            throw new IllegalArgumentException("There is no such region in DB");
        }
    }

    @Override
    public double getAverageTemperatureFromCountry(WeatherApi weatherApi, String country) {
        Optional<Country> countryObj = countryRepository.findByName(country);
        if(countryObj.isPresent()) {
            List<Region> regions = regionRepository.findAllByCountry_Id(countryObj.get().getId());
            double totalTemp=0.0;
            for (var region:regions) {
                totalTemp+=getAverageTemperatureFromRegion(weatherApi,region.getName());
            }
            double AvgTemp = totalTemp / regions.size();
            return AvgTemp;
        }
        else {
            throw new IllegalArgumentException("There is no such country in DB");
        }
    }


}
