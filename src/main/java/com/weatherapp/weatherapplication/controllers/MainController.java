package com.weatherapp.weatherapplication.controllers;

import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.YandexApi;
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

    //@Autowired
    //private WeatherApi weatherApi;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }

    @GetMapping("/weather/Yandex/{city}")
    public ResponseEntity<String> getTemperatureYand(@PathVariable("city") String city) {
        try {
            YandexApi weatherApi = new YandexApi();
        String temperature = String.valueOf(weatherServiceImpl.getTemperatureFromCity(weatherApi, city));
        return ResponseEntity.ok(temperature);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/weather/OpenWeather/{city}")
    public ResponseEntity<String> getTemperatureOW(@PathVariable("city") String city) {
        try {
            OpenWeatherApi weatherApi = new OpenWeatherApi();
            String temperature = String.valueOf(weatherServiceImpl.getTemperatureFromCity(weatherApi, city));
            return ResponseEntity.ok(temperature);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}