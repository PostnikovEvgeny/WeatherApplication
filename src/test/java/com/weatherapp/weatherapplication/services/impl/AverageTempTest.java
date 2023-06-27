package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.models.Country;
import com.weatherapp.weatherapplication.models.Region;
import com.weatherapp.weatherapplication.repository.CityRepository;
import com.weatherapp.weatherapplication.repository.RegionRepository;
import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@SpringBootTest
public class AverageTempTest {

    @Mock
    private WeatherApi weatherApi;
    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private WeatherServiceImpl weatherService;
    @Mock
    OpenWeatherApi openWeather;
    @Mock
    RegionRepository regionRepository;
    @Test
    public void testGetAverageTemperatureFromRegion() {
        Region region = new Region(1l,"Perm_region",new Country(1l,"Russia"));
        City city1 = new City(1l,"TestCity1",20,25, region);
        City city2 = new City(2l,"TestCity2",21,24, region);
        City city3 = new City(3l,"TestCity3",22,23, region);
        List<City> cities = new ArrayList<City>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        when(cityRepository.findAllByRegion_Id(region.getId())).thenReturn(cities);
        when(weatherService.getTemperatureFromCity(openWeather,city1)).thenReturn(15.0);
        when(weatherService.getTemperatureFromCity(openWeather,city2)).thenReturn(15.0);
        when(weatherService.getTemperatureFromCity(openWeather,city3)).thenReturn(15.0);
        double expected = 15.0;
        double actual = weatherService.getAverageTemperatureFromRegion(openWeather, region);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAverageTemperatureFromRegionWithNullRegion() {
        Region region =null;
        assertThrows(IllegalArgumentException.class,
                () -> {
                    weatherService.getAverageTemperatureFromRegion(weatherApi, region);
                });
    }

    @Test
    public void testGetAverageTemperatureFromCountry() {
        //Region region = new Region(1l,"Perm_region",new Country(1l,"Russia"));
        Country country = new Country(1l,"Russia");
        Region region1 = new Region(1l,"Perm_region",country);
        Region region2 = new Region(2l,"Moscow_region",country);
        Region region3 = new Region(3l,"Sverdlovsk_region",country);
        List<Region> regions = new ArrayList<Region>();
        regions.add(region1);
        regions.add(region2);
        regions.add(region3);
        City city1 = new City(1l,"TestCity1",20,25, region1);
        City city2 = new City(2l,"TestCity2",21,24, region1);
        City city3 = new City(3l,"TestCity3",22,23, region1);
        List<City> cities = new ArrayList<City>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        when(cityRepository.findAllByRegion_Id(region1.getId())).thenReturn(cities);
        when(cityRepository.findAllByRegion_Id(region2.getId())).thenReturn(cities);
        when(cityRepository.findAllByRegion_Id(region3.getId())).thenReturn(cities);
        when(regionRepository.findAllByCountry_Id(country.getId())).thenReturn(regions);
        when(weatherService.getAverageTemperatureFromRegion(openWeather,region1)).thenReturn(15.0);
        when(weatherService.getAverageTemperatureFromRegion(openWeather,region2)).thenReturn(15.0);
        when(weatherService.getAverageTemperatureFromRegion(openWeather,region3)).thenReturn(15.0);
        double expected = 15.0;
        double actual = weatherService.getAverageTemperatureFromCountry(openWeather, country);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAverageTemperatureFromRegionWithNullCountry() {
        Country country =null;
        assertThrows(IllegalArgumentException.class,
                () -> {
                    weatherService.getAverageTemperatureFromCountry(weatherApi, country);
                });
    }
}
