package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.ProductDAOInt;
import com.example.ecommerce.model.entities.Product;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ProductDAO  implements ProductDAOInt {

    @Override
    public void save(Product product, EntityManager em) {
        em.persist(product);
    }

    @Override
    public Optional<Product> get(long id, EntityManager em) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public void update(Product product, EntityManager em) {
        em.persist(product);
    }

    @Override
    public void delete(Product product, EntityManager em) {
        em.remove(product);
    }


    @Override
    public List<Product> getAllProductsByCategoryId(int categoryId, EntityManager em) {
        // Use JPQL to fetch products by Category_id
        //p.category ---> from product entity
        //id ---> from category entity
        return em.createQuery(
                        "SELECT p FROM Product p WHERE p.category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }


    public List<Product> getTenProducts(EntityManager em) {
        System.out.println("ProductDAO getTenProducts() called");
        List<Product> list =em.createQuery("SELECT p FROM Product p", Product.class)
                .setMaxResults(10)
                .getResultList();

        System.out.println("ProductDAO getTenProducts() end");
        return list;
    }
    public List<String> getProductImagesByProductId(int productId, EntityManager em) {
        return em.createQuery("SELECT p.imageUrl FROM ProductImage p WHERE p.product.id = :productId", String.class)
                .setParameter("productId", productId)
                .getResultList();
    }
}
