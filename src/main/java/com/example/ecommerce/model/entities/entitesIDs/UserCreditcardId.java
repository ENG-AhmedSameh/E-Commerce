package com.example.ecommerce.model.entities.entitesIDs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserCreditcardId implements Serializable {
    private static final long serialVersionUID = 6077229219005143607L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "credit_card_id", nullable = false)
    private Long creditCardId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserCreditcardId entity = (UserCreditcardId) o;
        return Objects.equals(this.creditCardId, entity.creditCardId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardId, userId);
    }

}