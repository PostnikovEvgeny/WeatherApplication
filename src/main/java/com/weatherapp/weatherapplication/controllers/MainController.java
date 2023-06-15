package com.weatherapp.weatherapplication.controllers;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<String> getTemperature(@PathVariable("city") String city) {
        try {
        String temperature = String.valueOf(weatherService.getTemperature(city));
        return ResponseEntity.ok(temperature);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}