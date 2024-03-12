package com.example.ecommerce.model.mappers;

import com.example.ecommerce.model.DTO.CartDto;
import com.example.ecommerce.model.entities.Cart;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CartMapper {
    Cart toEntity(CartDto cartDto);

    CartDto toDto(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cart partialUpdate(CartDto cartDto, @MappingTarget Cart cart);
}