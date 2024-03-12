package com.example.ecommerce.model.DTO;

import com.example.ecommerce.model.entities.entitiesID.CartItemId;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.ecommerce.model.entities.CartItem}
 */
@Value
public class CartItemDto implements Serializable {
    CartItemId id;
    CartDto cart;
    ProductDto product;
    Short quantity;
}