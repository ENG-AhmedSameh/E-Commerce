package com.example.ecommerce.model.mappers;

import com.example.ecommerce.model.DTO.CartItemDto;
import com.example.ecommerce.model.entities.CartItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CartItemMapper {
    CartItem toEntity(CartItemDto cartItemDto);

    CartItemDto toDto(CartItem cartItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartItem partialUpdate(CartItemDto cartItemDto, @MappingTarget CartItem cartItem);
}