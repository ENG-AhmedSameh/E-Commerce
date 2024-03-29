package com.example.ecommerce.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "product", schema = "e_commerce", indexes = {
        @Index(name = "fk_products_Categories1_idx", columnList = "Category_id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Category_id", nullable = false)
    private Category category;

    @Column(name = "price", precision = 9, scale = 2)
    private BigDecimal price;

    @Column(name = "Discount_percentage")
    private Byte discountPercentage;

    @Column(name = "quantity")
    private Integer availableQuantity;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 45)
    @Column(name = "main_image_url", length = 45)
    private String mainImageUrl;

    @OneToMany(mappedBy = "product" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<ProductImage> productImages = new LinkedHashSet<>();

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Byte isDeleted;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<CartItem> cartItems = new HashSet<>();

    public void addProductImage(ProductImage productImage) {
        productImages.add(productImage);
        productImage.setProduct(this);
    }

    public void removeProductImage(ProductImage productImage) {
        productImages.remove(productImage);
        productImage.setProduct(null);
    }
}