package com.example.ecommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.ecommerce.model.entities.Address}
 */
@Value
public class AddressDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 45)
    String city;
    @Size(max = 45)
    String street;
    @Size(max = 45)
    String buildingNumber;
    Byte floorNumber;
}