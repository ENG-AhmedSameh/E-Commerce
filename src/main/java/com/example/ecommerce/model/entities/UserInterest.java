package com.example.ecommerce.model.entities;

import com.example.ecommerce.model.entities.entitesIDs.UserInterestId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_interest", schema = "e_commerce", indexes = {
        @Index(name = "fk_user_interests_users1_idx", columnList = "user_id"),
        @Index(name = "fk_user_interests_Categories1_idx", columnList = "Category_id")
})
public class UserInterest {
    @EmbeddedId
    private UserInterestId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Category_id", nullable = false)
    private Category category;

}