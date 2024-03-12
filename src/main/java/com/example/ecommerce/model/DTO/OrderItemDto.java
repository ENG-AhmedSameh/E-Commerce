package com.example.ecommerce.model.DTO;

import com.example.ecommerce.model.entities.entitiesID.OrderItemId;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.ecommerce.model.entities.OrderItem}
 */
@Value
public class OrderItemDto implements Serializable {
    OrderItemId id;
    ProductDto product;
    BigDecimal price;
    Short quantity;
}