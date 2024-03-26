package com.example.ecommerce.model.DTO;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.ecommerce.model.entities.Cart}
 */
@Value
public class CartDto implements Serializable {
    Integer id;
    Set<CartItemDto> cartItems;
}