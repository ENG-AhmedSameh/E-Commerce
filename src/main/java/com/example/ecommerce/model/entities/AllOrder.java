package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class AllOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "create_time")
    private Date createTime;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "Address_id")
    private Address address;

}

