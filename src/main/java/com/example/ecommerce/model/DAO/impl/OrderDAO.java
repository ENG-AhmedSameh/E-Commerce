package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.OrderDAOInt;
import com.example.ecommerce.model.entities.Order;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class OrderDAO implements OrderDAOInt {

    @Override
    public void save(Order order, EntityManager em) {
        em.persist(order);
    }

    @Override
    public Optional<Order> get(Integer id, EntityManager em) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    @Override
    public void update(Order order, EntityManager em) {
        em.persist(order);
    }

    @Override
    public void delete(Order order, EntityManager em) {
        em.remove(order);
    }

}

