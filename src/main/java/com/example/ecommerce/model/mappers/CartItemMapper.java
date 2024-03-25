package com.example.ecommerce.model.mappers;

import com.example.ecommerce.model.DTO.CartItemDto;
import com.example.ecommerce.model.entities.CartItem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);
    CartItem toEntity(CartItemDto cartItemDto);

    CartItemDto toDto(CartItem cartItem);

    Set<CartItem> toEntitySet(Set<CartItemDto> cartItemDtos);
    Set<CartItemDto> toDtoSet(Set<CartItem> cartItems);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartItem partialUpdate(CartItemDto cartItemDto, @MappingTarget CartItem cartItem);
}