package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expire_date")
    private LocalDateTime expireDate;
    private BigDecimal balance;
    private Short cvv;



}

