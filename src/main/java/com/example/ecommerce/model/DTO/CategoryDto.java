package com.example.ecommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.ecommerce.model.entities.Category}
 */
@Value
public class CategoryDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 45)
    String name;
}