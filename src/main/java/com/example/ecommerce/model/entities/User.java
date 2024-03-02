package com.example.ecommerce.model.entities;

import com.example.ecommerce.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name",unique = true)
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number",unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(name = "credit_limit")
    private double creditLimit;

    private String job;

    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "Address_id")
    private Address address;


    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "user")
    private Cart cart;

    @ManyToMany
    @JoinTable(
            name = "user_Interest",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> Interests;

    @ManyToMany
    @JoinTable(
            name = "user_creditCard",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "creditcard_id")
    )
    private Set<CreditCard> creditCards;

}
