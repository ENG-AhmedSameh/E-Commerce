package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.CategoryDAOInt;
import com.example.ecommerce.model.entities.Category;
import com.example.ecommerce.model.entities.Product;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class CategoryDAO implements CategoryDAOInt {

    @Override
    public void save(Category category, EntityManager em) {
        em.persist(category);
    }

    @Override
    public Optional<Category> get(long id, EntityManager em) {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    @Override
    public void update(Category category, EntityManager em) {
        em.persist(category);
    }

    @Override
    public void delete(Category category, EntityManager em) {
        em.remove(category);
    }


}
