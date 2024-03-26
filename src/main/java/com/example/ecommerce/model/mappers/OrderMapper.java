package com.example.ecommerce.model.mappers;

import com.example.ecommerce.model.DTO.OrderDto;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.entities.Order;
import com.example.ecommerce.model.entities.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);

    List<OrderDto> toListDto (List<Order> orders);
}