package com.example.ecommerce.model.DTO;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link com.example.ecommerce.model.entities.Product}
 */
@Value
public class ProductDto implements Serializable {
    Integer id;
    @Size(max = 255)
    String name;
    BigDecimal price;
    Byte discountPercentage;
    Integer availableQuantity;
    CategoryDto category;
    @Size(max = 255)
    String description;
    @Size(max = 45)
    String mainImageUrl;
    Byte isDeleted;
    Set<ProductImageDto> productImages;
}