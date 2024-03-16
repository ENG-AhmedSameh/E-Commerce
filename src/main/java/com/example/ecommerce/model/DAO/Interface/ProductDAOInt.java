package com.example.ecommerce.model.DAO.Interface;

import com.example.ecommerce.model.entities.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface ProductDAOInt extends  DAO<Product> {
    List<Product> getAllProductsByCategoryId(int categoryId, EntityManager em);

}
