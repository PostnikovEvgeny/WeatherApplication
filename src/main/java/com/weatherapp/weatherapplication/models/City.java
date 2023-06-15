package com.weatherapp.weatherapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private double lat;
    private double lon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public City() {
    }

    public City(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }


}
