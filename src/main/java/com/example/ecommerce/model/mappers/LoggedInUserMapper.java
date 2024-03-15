package com.example.ecommerce.model.mappers;

import com.example.ecommerce.model.DTO.LoggedInUserDto;
import com.example.ecommerce.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoggedInUserMapper {
    LoggedInUserMapper INSTANCE = Mappers.getMapper(LoggedInUserMapper.class);
    LoggedInUserDto toDto(User user);
    User toEntity(LoggedInUserDto loggedInUser);
}
