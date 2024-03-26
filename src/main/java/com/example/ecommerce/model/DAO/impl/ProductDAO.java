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
    public Optional<Product> get(Integer id, EntityManager em) {
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
        String queryStr = "SELECT p FROM Product p ";
        List<Product> list =em.createQuery(queryStr, Product.class)
                .setMaxResults(10)
                .getResultList();

        System.out.println("ProductDAO getTenProducts() end");
        return list;
    }

    public List<String> getProductImagesByProductId(int productId, EntityManager em) {
        return em.createQuery("SELECT p.id.imageUrl FROM ProductImage p WHERE p.id.productId = :productId", String.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    public  List<Product> getProductsAll(EntityManager em){
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public void editProduct(EntityManager em, Product product) {
        em.createQuery("UPDATE Product p SET p.name = :newName, p.description = :newDescription, " +
                        "p.availableQuantity = :newQuantity, p.price = :newPrice, p.discountPercentage = :newDiscount, " +
                        "p.mainImageUrl = :newMainImage, p.category = :newCategory " +
                        "WHERE p.id = :productId")
                .setParameter("newName", product.getName())
                .setParameter("newDescription", product.getDescription())
                .setParameter("newQuantity", product.getAvailableQuantity())
                .setParameter("newPrice", product.getPrice())
                .setParameter("newDiscount", product.getDiscountPercentage())
                .setParameter("newMainImage", product.getMainImageUrl())
                .setParameter("newCategory", product.getCategory())
                .setParameter("productId", product.getId())
                .executeUpdate();
    }

    public void deleteProduct(EntityManager em, Product product) {
        em.createQuery("UPDATE Product p SET p.isDeleted = 1 WHERE p.id = :productId")
                .setParameter("productId", product.getId())
                .executeUpdate();
    }

    public Product getProductById(int productId, EntityManager em){
        return em.find(Product.class, productId);
    }

    public void updateProductQuantity(Product product, int boughtQuantity, EntityManager em) {
        em.createQuery("UPDATE Product p SET p.availableQuantity = :newQuantity WHERE p.id = :productId")
                .setParameter("newQuantity", product.getAvailableQuantity()-boughtQuantity)
                .setParameter("productId", product.getId())
                .executeUpdate();
    }
}
