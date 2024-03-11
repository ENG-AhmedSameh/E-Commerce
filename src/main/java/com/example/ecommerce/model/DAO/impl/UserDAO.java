package com.example.ecommerce.model.DAO.impl;


import com.example.ecommerce.model.DAO.Interface.UserDAOInt;
import com.example.ecommerce.model.entities.User;
import jakarta.persistence.EntityManager;

import java.util.Optional;


public class UserDAO implements UserDAOInt  {

    @Override
    public void save(User user,EntityManager em) {
        em.persist(user);
    }

    @Override
    public Optional<User> get(long id,EntityManager em) {
        return Optional.ofNullable(em.find(User.class,id));
    }

    @Override
    public void update(User user,EntityManager em) {
        em.persist(user);
    }

    @Override
    public void delete(User user,EntityManager em) {
        em.remove(user);
    }

//    @Override
//    public boolean updatePassword(long id, String password,EntityManager em) {
//        return false;
//    }
//
//    @Override
//    public boolean updatePhoneNumber(long id, String phoneNumber,EntityManager em) {
//        return false;
//    }
}
