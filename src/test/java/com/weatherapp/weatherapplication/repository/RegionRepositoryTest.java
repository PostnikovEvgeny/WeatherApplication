package com.weatherapp.weatherapplication.repository;

import com.weatherapp.weatherapplication.models.City;
import com.weatherapp.weatherapplication.models.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RegionRepositoryTest {
    @Autowired
    private RegionRepository regionRepository;
    @Test
    void findByName() {
        Region region = regionRepository.findByName("Perm_region");
        String name = "Perm_region";
        assertEquals(region.getName(), name);
    }

    @Test
    void findAllByCountry_Id() {
        List<Region> regions = regionRepository.findAllByCountry_Id(1l);
        List<String> regionActualNames = new ArrayList<>();
        for (Region region:regions) {
            regionActualNames.add(region.getName());
        }
        List<String> regionsExpectedNames = new ArrayList<>();
        regionsExpectedNames.add("Perm_region");
        regionsExpectedNames.add("Moscow_region");
        regionsExpectedNames.add("Sverdlovsk_region");
        assertEquals(regionsExpectedNames,regionActualNames);
    }
}