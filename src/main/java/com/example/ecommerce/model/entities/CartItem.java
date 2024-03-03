package com.example.ecommerce.model.entities;

import com.example.ecommerce.model.entities.entitesIDs.CartItemId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_item", schema = "e_commerce", indexes = {
        @Index(name = "fk_cart_item_Cart1_idx", columnList = "Cart_id"),
        @Index(name = "fk_cart_item_product1_idx", columnList = "product_id")
})
public class CartItem {
    @EmbeddedId
    private CartItemId id;

    @MapsId("cartId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Cart_id", nullable = false, referencedColumnName = "id")
    private Cart cart;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity")
    private Short quantity;

}