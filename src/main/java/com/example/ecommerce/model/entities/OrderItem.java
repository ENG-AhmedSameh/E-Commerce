package com.example.ecommerce.model.entities;

import com.example.ecommerce.model.entities.entitiesID.CartItemId;
import com.example.ecommerce.model.entities.entitiesID.OrderItemId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_item")
@NoArgsConstructor
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity")
    private Short quantity;


    public OrderItem(Order order, Product product, BigDecimal price, Short quantity) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.id = new OrderItemId(order.getId(),product.getId());
    }

}