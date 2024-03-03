package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "credit_card", schema = "e_commerce")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "card_number", nullable = false, length = 45)
    private String cardNumber;

    @NotNull
    @Column(name = "expire_Date", nullable = false)
    private LocalDate expireDate;

    @NotNull
    @Column(name = "balance", nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    @NotNull
    @Column(name = "cvv", nullable = false)
    private Byte cvv;

    @ManyToMany
    @JoinTable(name = "user_creditcard",
            joinColumns = @JoinColumn(name = "credit_card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new LinkedHashSet<>();

}