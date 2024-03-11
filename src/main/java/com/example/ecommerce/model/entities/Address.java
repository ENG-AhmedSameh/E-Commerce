package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "e_commerce")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    @OneToMany(mappedBy = "address")
    private Set<AllOrder> allOrders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "address")
    private Set<User> users = new LinkedHashSet<>();

}