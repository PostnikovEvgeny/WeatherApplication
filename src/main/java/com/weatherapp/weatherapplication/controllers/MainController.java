package com.weatherapp.weatherapplication.controllers;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.models.Country;
import com.weatherapp.weatherapplication.models.Region;
import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.repository.CountryRepository;
import com.weatherapp.weatherapplication.repository.RegionRepository;
import com.weatherapp.weatherapplication.services.ProviderDetectorService;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private WeatherServiceImpl weatherServiceImpl;
    @Autowired
    private WeatherApi weatherApi;
    @Autowired
    private ProviderDetectorService providerDetectorService;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<String> getTemperatureApi(@PathVariable("city") String city, @RequestParam(value = "provider", required = false) String provider) {
        try {
            City cityObj = cityRepository.findByName(city);
            weatherApi = providerDetectorService.DetectTheProvider(provider);
            String temperature = String.valueOf(weatherServiceImpl.getTemperatureFromCity(weatherApi, cityObj));
            return ResponseEntity.ok(temperature);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/weather/AverageRegion/{region}")
    public ResponseEntity<String> getAverageTempFromRegion(@PathVariable("region") String region, @RequestParam(value = "provider", required = false) String provider) {
        try {
            Region regionObj = regionRepository.findByName(region);
            weatherApi = providerDetectorService.DetectTheProvider(provider);
            String AvgRegionTemp = String.valueOf(weatherServiceImpl.getAverageTemperatureFromRegion(weatherApi, regionObj));
            return ResponseEntity.ok(AvgRegionTemp);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/weather/AverageCountry/{country}")
    public ResponseEntity<String> getAverageTempFromCountry(@PathVariable("country") String country, @RequestParam(value = "provider", required = false) String provider) {
        try {
            Country countryObj = countryRepository.findByName(country);
            weatherApi = providerDetectorService.DetectTheProvider(provider);
            String AvgCountryTemp = String.valueOf(weatherServiceImpl.getAverageTemperatureFromCountry(weatherApi, countryObj));
            return ResponseEntity.ok(AvgCountryTemp);

        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}