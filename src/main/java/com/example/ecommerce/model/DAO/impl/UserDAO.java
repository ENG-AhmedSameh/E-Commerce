package com.example.ecommerce.model.DAO.impl;


import com.example.ecommerce.model.DAO.Interface.UserDAOInt;
import com.example.ecommerce.model.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class UserDAO implements UserDAOInt  {

    @Override
    public void save(User user,EntityManager em) {
        em.persist(user);
    }

    @Override
    public Optional<User> get(Integer id,EntityManager em) {
        return Optional.ofNullable(em.find(User.class,id));
    }

    @Override
    public void update(User user,EntityManager em) {
        em.merge(user);
    }

    @Override
    public void delete(User user,EntityManager em) {
        em.remove(user);
    }

    @Override
    public User getUserByEmail(String email, EntityManager em) {
        try {
            Query query = em.createQuery("select u from User u where u.email=:email");
            query.setParameter("email", email);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getUserByUsername(String username, EntityManager em) {
        try {
            Query query = em.createQuery("select u from User u where u.userName=:username");
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getUserById(Integer id, EntityManager em) {
        return em.find(User.class, id);
    }

    public List<User> getAllUsers(EntityManager em) {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public void updateCreditLimit(User user, BigDecimal currentLimit, EntityManager em) {
        String jpql = "UPDATE User u SET u.creditLimit = :newCreditLimit WHERE u.id = :userId";

        // Execute the update query
        int updatedCount = em.createQuery(jpql)
                .setParameter("newCreditLimit", currentLimit)
                .setParameter("userId", user.getId())
                .executeUpdate();
    }
}
