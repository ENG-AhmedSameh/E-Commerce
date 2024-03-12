package com.example.ecommerce.model.mappers;

import com.example.ecommerce.model.DTO.AddressDto;
import com.example.ecommerce.model.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    AddressDto addressToAddressDto(Address address);
    Address addressDtoToAddress(AddressDto addressDto);
}
