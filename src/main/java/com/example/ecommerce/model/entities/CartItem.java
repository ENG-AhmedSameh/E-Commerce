package com.example.ecommerce.model.entities;

import com.example.ecommerce.model.entities.entitiesID.CartItemId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_item", schema = "e_commerce", indexes = {
        @Index(name = "fk_cart_item_Cart1_idx", columnList = "Cart_id"),
        @Index(name = "fk_cart_item_product1_idx", columnList = "product_id")
})
@NoArgsConstructor
public class CartItem {
    @EmbeddedId
    private CartItemId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @MapsId("cartId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Cart_id", nullable = false)
    private Cart cart;

    @Column(name = "quantity")
    private Short quantity;


    public CartItem(Product product,Cart cart,Short quantity){
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
        this.id = new CartItemId(cart.getId(),product.getId());
    }

}