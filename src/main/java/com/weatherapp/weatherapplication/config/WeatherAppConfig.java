package com.weatherapp.weatherapplication.config;

import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherAppConfig {

    @Bean
    public RestTemplate restTemplate() {
     return new RestTemplate();
   }

   //@Bean
   // public WeatherServiceImpl weatherServiceImpl() {
   //    return  new WeatherServiceImpl();
  // }

    //https://csharpcoderr.com/5539/

}
