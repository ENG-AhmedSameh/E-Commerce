package com.example.ecommerce.model.DAO.Interface;

import com.example.ecommerce.model.DAO.impl.UserDAO;
import com.example.ecommerce.model.entities.User;

public interface UserDAOInt extends  DAO<User>{

    boolean updatePassword(long id , String password);

    boolean updatePhoneNumber(long id , String phoneNumber);


}
