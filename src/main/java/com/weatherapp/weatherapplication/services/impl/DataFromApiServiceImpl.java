/*
package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.services.DataFromApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
@Service
public class DataFromApiServiceImpl implements DataFromApiService {
    @Override
    public String getDataFromApi(String apiKey, double lat, double lon) {
        return null;
    }

    //@Autowired
    //private RestTemplate restTemplate;
    */
/*@Override
    public String getDataFromApi(String apiKey, double lat, double lon) {
        //String url = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
        String urlApi = "https://api.weather.yandex.ru/v2/forecast?lat={lat}&lon={lon}";
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("X-Yandex-API-Key", "2e934e98-a0b4-44e8-8f6f-829321eb075e");
        //HttpEntity<String> entity = new HttpEntity<String>(headers);
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, lat, lon, entity);

        URI url = UriComponentsBuilder.fromHttpUrl(urlApi)
                .build(lat, lon);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Yandex-API-Key", apiKey);

        HttpEntity entity = new HttpEntity(headers);

        *//*
*/
/*ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );*//*
*/
/*


        //Map<String, String> params = new HashMap<>();
        //params.put("lat", Double.toString(lat));
        //params.put("lon", Double.toString(lon));
        //params.put("apiKey", apiKey);
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, params);
        String weatherData = responseEntity.getBody();
        System.out.println(weatherData);
        return weatherData;
    }*//*

}
*/
