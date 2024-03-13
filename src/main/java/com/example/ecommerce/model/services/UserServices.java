package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.UserDAO;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.model.mappers.UserMapper;
import com.example.ecommerce.model.util.PasswordManager;

public class UserServices {
    public static UserDto registerNewUser( UserDto userDto ) {
        byte[] salt = PasswordManager.generateSalt();
        String hashedPassword = PasswordManager.encode(userDto.getPassword(), salt);
        User user = UserMapper.INSTANCE.toEntity(userDto);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        Cart cart = new Cart();
        user.setCart(cart);
        UserDAO userDAO = new UserDAO();
        Database.doInTransactionWithoutResult(em ->
                userDAO.save(user, em)
        );
        return UserMapper.INSTANCE.toDto(user);
    }
}
