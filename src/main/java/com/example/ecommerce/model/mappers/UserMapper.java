package com.example.ecommerce.model.mappers;

import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.entities.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);

    List<UserDto> toListDto (List<User> users);
}