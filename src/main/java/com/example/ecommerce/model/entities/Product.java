package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    @Column(name = "discount_percentage")
    private Byte discountPercentage;

    @Column(name = "available_quantity")
    private Integer availableQuantity;
    private String description;

    @Column(name = "main_image_url")
    private String mainImageUrl;

    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;

}
