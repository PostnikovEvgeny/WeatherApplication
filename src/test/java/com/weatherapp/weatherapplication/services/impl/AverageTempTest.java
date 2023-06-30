package com.weatherapp.weatherapplication.services.impl;

import com.weatherapp.weatherapplication.DB.models.City;
import com.weatherapp.weatherapplication.DB.models.Country;
import com.weatherapp.weatherapplication.DB.models.Region;
import com.weatherapp.weatherapplication.DB.repository.CityRepository;
import com.weatherapp.weatherapplication.DB.repository.CountryRepository;
import com.weatherapp.weatherapplication.DB.repository.RegionRepository;
import com.weatherapp.weatherapplication.services.OpenWeatherApi;
import com.weatherapp.weatherapplication.services.WeatherApi;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
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
    @Mock
    CountryRepository countryRepository;
    @Test
    public void testGetAverageTemperatureFromRegion() {
        Region region = new Region(1l,"Perm_region",new Country(1l,"Russia"));
        City city1 = new City(1l,"TestName1",20,25, region);
        City city2 = new City(2l,"TestName2",21,24, region);
        City city3 = new City(3l,"TestName3",22,23, region);
        List<City> cities = new ArrayList<City>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        when(cityRepository.findAllByRegion_Id(region.getId())).thenReturn(cities);
        when(regionRepository.findByName("Perm_region")).thenReturn(Optional.of(region));
        when(cityRepository.findByName("TestName1")).thenReturn(Optional.of(city1));
        when(cityRepository.findByName("TestName2")).thenReturn(Optional.of(city2));
        when(cityRepository.findByName("TestName3")).thenReturn(Optional.of(city3));
        when(weatherService.getTemperatureFromCity(openWeather,city1.getName())).thenReturn(15.0);
        double expected = 15.0;
        double actual = weatherService.getAverageTemperatureFromRegion(openWeather, region.getName());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAverageTemperatureFromRegionWithNullRegion() {
        Region region =null;
        assertThrows(NullPointerException.class,
                () -> {
                    weatherService.getAverageTemperatureFromRegion(weatherApi, region.getName());
                });
    }

    @Test
    public void testGetAverageTemperatureFromCountry() {
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
        when(countryRepository.findByName("Russia")).thenReturn(Optional.of(country));
        when(cityRepository.findAllByRegion_Id(region1.getId())).thenReturn(cities);
        when(cityRepository.findAllByRegion_Id(region2.getId())).thenReturn(cities);
        when(cityRepository.findAllByRegion_Id(region3.getId())).thenReturn(cities);
        when(regionRepository.findByName("Perm_region")).thenReturn(Optional.of(region1));
        when(regionRepository.findByName("Moscow_region")).thenReturn(Optional.of(region2));
        when(regionRepository.findByName("Sverdlovsk_region")).thenReturn(Optional.of(region3));
        when(regionRepository.findAllByCountry_Id(country.getId())).thenReturn(regions);
        when(cityRepository.findByName("TestCity1")).thenReturn(Optional.of(city1));
        when(cityRepository.findByName("TestCity2")).thenReturn(Optional.of(city2));
        when(cityRepository.findByName("TestCity3")).thenReturn(Optional.of(city3));
        when(weatherService.getTemperatureFromCity(openWeather,city1.getName())).thenReturn(15.0);
        when(weatherService.getAverageTemperatureFromRegion(openWeather,region1.getName())).thenReturn(15.0);
        when(weatherService.getAverageTemperatureFromRegion(openWeather,region2.getName())).thenReturn(15.0);
        when(weatherService.getAverageTemperatureFromRegion(openWeather,region3.getName())).thenReturn(15.0);
        double expected = 15.0;
        double actual = weatherService.getAverageTemperatureFromCountry(openWeather, country.getName());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAverageTemperatureFromRegionWithNullCountry() {
        Country country =null;
        assertThrows(NullPointerException.class,
                () -> {
                    weatherService.getAverageTemperatureFromCountry(weatherApi, country.getName());
                });
    }
}
