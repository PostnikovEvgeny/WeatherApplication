package com.weatherapp.weatherapplication.config;

import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import com.weatherapp.weatherapplication.services.YandexApi;
import com.weatherapp.weatherapplication.services.impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherAppConfig {

    @Bean
    public RestTemplate restTemplate() {
     return new RestTemplateBuilder().build();
   }

   // @Bean
  //  public YandexApi yandexApi(){
 //       return new YandexApi(restTemplate());
 //   }
   // @Bean
   // public OpenWeatherApi openWeatherApi(){
   //     return new OpenWeatherApi(restTemplate());
    //}
    @Bean
    public WeatherApi weatherApi(){
        return new OpenWeatherApi(restTemplate());
    }

}
