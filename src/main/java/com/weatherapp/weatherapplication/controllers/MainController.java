package com.weatherapp.weatherapplication.controllers;

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
    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<String> getTemperatureApi(@PathVariable("city") String city, @RequestParam(value = "provider", required = false) String provider) {
        try {
            weatherApi = providerDetectorService.detectTheProvider(provider);
            double temperature = weatherServiceImpl.getTemperatureFromCity(weatherApi, city);
            return ResponseEntity.ok(String.valueOf(temperature));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Getting temperature error");
        }
    }
    @GetMapping("/weather/AverageTemp/Region/{region}")
    public ResponseEntity<String> getAverageTempFromRegion(@PathVariable("region") String region, @RequestParam(value = "provider", required = false) String provider) {
        try {
            weatherApi = providerDetectorService.detectTheProvider(provider);
            double AvgRegionTemp = weatherServiceImpl.getAverageTemperatureFromRegion(weatherApi, region);
            return ResponseEntity.ok(String.valueOf(AvgRegionTemp));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Getting temperature error");
        }
    }
    @GetMapping("/weather/AverageTemp/Country/{country}")
    public ResponseEntity<String> getAverageTempFromCountry(@PathVariable("country") String country, @RequestParam(value = "provider", required = false) String provider) {
        try {
            weatherApi = providerDetectorService.detectTheProvider(provider);
            double AvgCountryTemp = weatherServiceImpl.getAverageTemperatureFromCountry(weatherApi, country);
            return ResponseEntity.ok(String.valueOf(AvgCountryTemp));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Getting temperature error");
        }
    }

}