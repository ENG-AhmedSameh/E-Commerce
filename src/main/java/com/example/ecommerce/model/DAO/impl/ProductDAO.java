package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.ProductDAOInt;
import com.example.ecommerce.model.DAO.PersistenceManager;
import com.example.ecommerce.model.entities.Product;
import jakarta.persistence.EntityManager;

import java.util.concurrent.Callable;

public class ProductDAO  implements ProductDAOInt {
    public static EntityManager entityManager = PersistenceManager.getEntityManager();

    @Override
    public boolean insert(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product get(long id) {
        try {
            entityManager.getTransaction().begin();
            Product product=  entityManager.find(Product.class , id);
            entityManager.getTransaction().commit();
            return product;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
            return  true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
       try {
           entityManager.getTransaction().begin();
           entityManager.remove(product);
           entityManager.getTransaction().commit();

           return true;
       } catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }
}
