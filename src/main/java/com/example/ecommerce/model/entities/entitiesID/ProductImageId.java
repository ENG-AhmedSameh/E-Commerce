package com.example.ecommerce.model.entities.entitiesID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductImageId implements Serializable {
    private static final long serialVersionUID = -2548318977129672777L;
    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Size(max = 255)
    @NotNull
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public ProductImageId(Integer id, String imageUrl) {
        this.productId = id;
        this.imageUrl = imageUrl;
    }

    public ProductImageId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductImageId entity = (ProductImageId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.imageUrl, entity.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, imageUrl);
    }

}