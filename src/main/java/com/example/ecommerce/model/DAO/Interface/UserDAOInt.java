package com.example.ecommerce.model.DAO.Interface;

import com.example.ecommerce.model.entities.User;
import jakarta.persistence.EntityManager;

public interface UserDAOInt extends  DAO<User>{

    User getUserByEmail(String email, EntityManager em);

//    boolean updatePassword(long id , String password, EntityManager em);
//
//    boolean updatePhoneNumber(long id , String phoneNumber, EntityManager em);


}
