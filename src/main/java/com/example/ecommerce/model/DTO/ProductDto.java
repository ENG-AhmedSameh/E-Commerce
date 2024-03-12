package com.example.ecommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.ecommerce.model.entities.Product}
 */
@Value
public class ProductDto implements Serializable {
    Integer id;
    @Size(max = 255)
    String name;
    @NotNull
    CategoryDto category;
    BigDecimal price;
    Byte discountPercentage;
    Integer availableQuantity;
    @Size(max = 255)
    String description;
    @Size(max = 45)
    String mainImageUrl;
}