package com.weatherapp.weatherapplication.DB.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private double lat;

    private double lon;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

}
