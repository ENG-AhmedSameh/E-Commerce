package com.example.ecommerce.model.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.ecommerce.model.entities.Product}
 */
@Value
public class CartProductDto implements Serializable {
    Integer id;
    @Size(max = 255)
    String name;
    BigDecimal price;
    CategoryDto category;
    @Size(max = 45)
    String mainImageUrl;
    Short quantity;

    public CartProductDto(Integer id, String name, BigDecimal price, CategoryDto category, String mainImageUrl, Short quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.mainImageUrl = mainImageUrl;
        this.quantity = quantity;
    }
}