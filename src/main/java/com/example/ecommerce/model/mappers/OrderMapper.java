package com.example.ecommerce.model.mappers;

import com.example.ecommerce.model.DTO.OrderDto;
import com.example.ecommerce.model.entities.Order;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);
}