package com.example.ecommerce.model.entities;

import com.example.ecommerce.model.enums.Gender;
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
        @Index(name = "fk_users_Addresses_idx", columnList = "Address_id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 45)
    private String userName;

    @Size(max = 32)
    @NotNull
    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Size(max = 16)
    @NotNull
    @Column(name = "Salt", nullable = false, length = 16)
    private byte [] salt;


    @Size(max = 45)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Size(max = 45)
    @Column(name = "last_name", length = 45)
    private String lastName;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 15)
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

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST )
    @JoinColumn(name = "Address_id")
    private Address address;

    @NotNull
    @Lob
    @Column(name = "Gender", nullable = false)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private Set<AllOrder> allOrders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user" , cascade = { CascadeType.REMOVE , CascadeType.PERSIST} )
    private Set<Cart> carts = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "users" , cascade = CascadeType.PERSIST)
    private Set<CreditCard> creditCards = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "users" , cascade = {CascadeType.PERSIST , CascadeType.REMOVE})
    private Set<Category> categories = new LinkedHashSet<>();

//    public  void  setPassword (String password){
//        this.password = password;
//    }

}