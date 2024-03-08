package com.example.ecommerce.model.DAO.impl;


import com.example.ecommerce.model.DAO.Interface.UserDAOInt;
import com.example.ecommerce.model.DAO.PersistenceManager;
import com.example.ecommerce.model.entities.User;
import jakarta.persistence.EntityManager;


public class UserDAO  implements UserDAOInt  {

    public static EntityManager entityManager = PersistenceManager.getEntityManager();

    public UserDAO() {
    }

    public boolean insert(User t) {
      try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
          e.printStackTrace();
          return false;

      }
        return true;
    }

    public User get(long id) {
        try {
            return entityManager.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean update(User t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(User t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePassword(long id, String password) {
       try {
           entityManager.getTransaction().begin();
           User user = entityManager.find(User.class, id);
           user.setPassword(password);
           entityManager.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
       return true;
    }

    @Override
    public boolean updatePhoneNumber(long id, String phoneNumber) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            user.setPhoneNumber(phoneNumber);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
