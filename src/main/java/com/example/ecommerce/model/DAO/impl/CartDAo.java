package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.CartDAOInt;
import com.example.ecommerce.model.DAO.PersistenceManager;
import com.example.ecommerce.model.entities.Cart;
import jakarta.persistence.EntityManager;

public class CartDAo implements CartDAOInt {
    public static EntityManager entityManager = PersistenceManager.getEntityManager();
    @Override
    public boolean insert(Cart cart) {
       try {
           entityManager.getTransaction().begin();
           entityManager.persist(cart);
           entityManager.getTransaction().commit();
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

    @Override
    public Cart get(long id) {
        try {
            entityManager.getTransaction().begin();
            Cart cart = entityManager.find(Cart.class , id);
            entityManager.getTransaction().commit();
            return cart;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Cart cart) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cart);
            entityManager.getTransaction().commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Cart cart) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(cart);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }
}
