package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.models.City;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findByNameTest(){
        City city = cityRepository.findByName("Perm");
        String name = city.getName();
        assertEquals("Perm", name);
    }

    @Test
    public void testInvalidCity(){
        City city = cityRepository.findByName("123");
        assertTrue(city == null);
    }

}