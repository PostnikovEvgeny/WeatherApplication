package com.weatherapp.weatherapplication.controllers;

import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.YandexApi;
import com.weatherapp.weatherapplication.services.impl.WeatherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @Autowired
    private WeatherServiceImpl weatherServiceImpl;
    @Autowired
    private WeatherApi weatherApi;
    @Autowired
    private YandexApi yandexApi;
    @Autowired
    private OpenWeatherApi openWeatherApi;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<String> getTemperatureApi(@PathVariable("city") String city, @RequestParam(value = "provider", required = false) String provider) {
        if(provider == null)
        {
            throw new IllegalArgumentException("Не указан провайдер");
        }
        try {
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

                    break;
            }
            String temperature = String.valueOf(weatherServiceImpl.getTemperatureFromCity(weatherApi, city));
            return ResponseEntity.ok(temperature);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}