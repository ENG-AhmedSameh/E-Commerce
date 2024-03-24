package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cart", schema = "e_commerce", indexes = {
        @Index(name = "fk_Cart_user1_idx", columnList = "user_id")
})
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItems = new LinkedHashSet<>();

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }
    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setCart(null);
    }

    public void addCartItem(Set<CartItem> cartItem) {
        if (cartItems != null)
        {
            cartItems.addAll(cartItem);
        }


    }
}