package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.AllOrderDAOInt;
import com.example.ecommerce.model.DAO.PersistenceManager;
import com.example.ecommerce.model.entities.AllOrder;
import jakarta.persistence.EntityManager;

public class AllOrderDAO implements AllOrderDAOInt {

    public static EntityManager entityManager = PersistenceManager.getEntityManager();

    public AllOrderDAO() {
    }

    public boolean insert(AllOrder t) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public AllOrder get(long id) {

        entityManager.getTransaction().begin();
        AllOrder order = entityManager.getReference(AllOrder.class , id);
        entityManager.getTransaction().commit();
        return order;


    }

    public boolean update(AllOrder t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean delete(AllOrder t) {
       try {
           entityManager.getTransaction().begin();
           entityManager.remove(t);
           entityManager.getTransaction().commit();
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

}

