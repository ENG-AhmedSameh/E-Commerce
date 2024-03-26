package com.example.ecommerce.model.DTO;

import com.example.ecommerce.model.entities.entitiesID.ProductImageId;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.ecommerce.model.entities.ProductImage}
 */
@Value
public class ProductImageDto implements Serializable {
    ProductImageId id;
//    ProductDto product;
}