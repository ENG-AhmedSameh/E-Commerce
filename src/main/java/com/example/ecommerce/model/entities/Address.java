package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Entity
@Table(name = "address", schema = "e_commerce")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "City", nullable = false, length = 45)
    private String city;

    @Size(max = 45)
    @Column(name = "street", length = 45)
    private String street;

    @Size(max = 45)
    @Column(name = "building_number", length = 45)
    private String buildingNumber;

    @Column(name = "floor_number")
    private Byte floorNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Byte getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Byte floorNumber) {
        this.floorNumber = floorNumber;
    }
}