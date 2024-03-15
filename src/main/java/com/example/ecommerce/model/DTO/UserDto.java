package com.example.ecommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO for {@link com.example.ecommerce.model.entities.User}
 */
@Value
public class UserDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 45)
    String userName;
    @NotNull
    @Size(max = 32)
    String password;
    @NotNull
    @Size(max = 45)
    String firstName;
    @Size(max = 45)
    String lastName;
    @NotNull
    @Size(max = 55)
    String phoneNumber;
    @NotNull
    @Size(max = 255)
    String email;
    @NotNull
    BigDecimal creditLimit;
    @Size(max = 255)
    String job;
    @NotNull
    String gender;

    @NotNull
    String city;
    @NotNull
    String street;

    @NotNull
    @Size(max = 16)
    byte[] salt;
//    Set<OrderDto> orders;
    CartDto cart;
//    Set<CategoryDto> categories;
}