package com.example.ecommerce.model.DAO.impl;

import com.example.ecommerce.model.DAO.Interface.CategoryDAOInt;
import com.example.ecommerce.model.DAO.PersistenceManager;
import com.example.ecommerce.model.entities.Category;
import jakarta.persistence.EntityManager;

public class CategoryDAO implements CategoryDAOInt {

    public static EntityManager entityManager = PersistenceManager.getEntityManager();
    @Override
    public boolean insert(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Category get(long id) {
      try {
          entityManager.getTransaction().begin();
          Category category = entityManager.find(Category.class , id);
          entityManager.getTransaction().commit();
          return category;
      }catch (Exception e){
          e.printStackTrace();
          return null;
      }
    }

    @Override
    public boolean update(Category category) {
       try {
           entityManager.getTransaction().begin();
           entityManager.merge(category);
           entityManager.getTransaction().commit();
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

    @Override
    public boolean delete(Category category) {
       try {
           entityManager.getTransaction().begin();
           entityManager.remove(category);
           entityManager.getTransaction().commit();

           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }
}
