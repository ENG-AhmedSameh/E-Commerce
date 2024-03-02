package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private AllOrder order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal price;
    private short quantity;

}

