package com.example.ecommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link com.example.ecommerce.model.entities.Order}
 */
@Value
public class OrderDto implements Serializable {
    Integer id;
    @NotNull
    UserDto user;
    @NotNull
    Instant createTime;
    @NotNull
    BigDecimal price;
    Set<OrderItemDto> orderItems;
}