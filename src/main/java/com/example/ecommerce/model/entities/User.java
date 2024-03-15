package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "e_commerce", indexes = {
        @Index(name = "user_name_UNIQUE", columnList = "user_name", unique = true),
        @Index(name = "phone_number_UNIQUE", columnList = "phone_number", unique = true),
        @Index(name = "email_UNIQUE", columnList = "email", unique = true),
        @Index(name = "fk_user_address1_idx", columnList = "address_id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 45)
    private String userName;

    @Size(max = 32)
    @NotNull
    @Column(name = "Password", nullable = false, length = 32)
    private String password;

    @Size(max = 45)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Size(max = 45)
    @Column(name = "last_name", length = 45)
    private String lastName;

    @Size(max = 55)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 55)
    private String phoneNumber;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "credit_limit", nullable = false, precision = 11, scale = 2)
    private BigDecimal creditLimit;

    @Size(max = 255)
    @Column(name = "job")
    private String job;

    @NotNull
    @Lob
    @Column(name = "Gender", nullable = false)
    private String gender;

    @Column(name = "City", nullable = false, length = 45)
    @NotNull
    private String City;

    @Column(name = "Street", nullable = false, length = 255)
    @NotNull
    private String Street;

    @Size(max = 16)
    @NotNull
    @Column(name = "Salt", nullable = true, length = 16)
    private byte[] salt;



    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Cart cart;

//    @ManyToMany(fetch = FetchType.LAZY)
    //    private Set<Category> categories = new LinkedHashSet<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Order> orders = new LinkedHashSet<>();
}