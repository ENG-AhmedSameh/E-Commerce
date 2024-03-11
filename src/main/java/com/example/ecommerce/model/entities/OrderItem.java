package com.example.ecommerce.model.entities;

import com.example.ecommerce.model.entities.entitiesID.OrderItemId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_item", schema = "e_commerce", indexes = {
        @Index(name = "fk_order_item_all_order1_idx", columnList = "order_id"),
        @Index(name = "fk_order_items_product1_idx", columnList = "product_id")
})
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity")
    private Short quantity;

}