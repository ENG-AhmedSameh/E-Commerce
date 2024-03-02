package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@Table(name = "cart_item")
public class CartItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "Cart_id")
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private short quantity;
}
