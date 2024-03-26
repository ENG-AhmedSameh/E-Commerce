package com.example.ecommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class LoggedInUserDto {
    Integer id;
    @NotNull
    @Size(max = 45)
    String userName;
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

    CartDto cart;
}
