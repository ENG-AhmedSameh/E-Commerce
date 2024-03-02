package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String street;
    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "floor_number")
    private Byte floorNumber;

}

