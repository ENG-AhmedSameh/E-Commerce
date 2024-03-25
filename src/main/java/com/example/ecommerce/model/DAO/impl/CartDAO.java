package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.CartDAOInt;
import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.Category;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class CartDAO implements CartDAOInt {
    @Override
    public void save(Cart cart, EntityManager em) {
        em.persist(cart);
    }

    @Override
    public Optional<Cart> get(Integer id, EntityManager em) {
        return Optional.ofNullable(em.find(Cart.class, id));
    }

    @Override
    public void update(Cart cart, EntityManager em) {
        em.merge(cart);
    }

    @Override
    public void delete(Cart cart, EntityManager em) {
        em.remove(cart);
    }
}
