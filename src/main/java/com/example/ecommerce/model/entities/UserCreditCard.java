package com.example.ecommerce.model.entities;

import com.example.ecommerce.model.entities.entitesIDs.UserCreditcardId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_creditcard", schema = "e_commerce", indexes = {
        @Index(name = "fk_user_creditcard_user1_idx", columnList = "user_id"),
        @Index(name = "fk_user_creditcard_credit_card1_idx", columnList = "credit_card_id")
})
public class UserCreditCard {
    @EmbeddedId
    private UserCreditcardId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("creditCardId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

}