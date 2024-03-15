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
        cart.setOwner(user);
        UserDAO userDAO = new UserDAO();
        Database.doInTransactionWithoutResult(em ->
                userDAO.save(user, em)
        );
        return UserMapper.INSTANCE.toDto(user);
    }

    public static UserDto loginUser(String userName, String password) {
        UserDAO userDAO = new UserDAO();
        User loggedUser = Database.doInTransaction(em -> {
            User user = userDAO.getUserByUsername(userName, em);
            if (user == null)
                return null;
            String hashedPassword = user.getPassword();
            byte[] salt = user.getSalt();
            if (PasswordManager.isEqual(hashedPassword, password, salt))
                return user;

            return null;
        });
        return UserMapper.INSTANCE.toDto(loggedUser);
    }

    public static boolean isUserNameAvailable(String username) {
        UserDAO userDAO = new UserDAO();
        return Database.doInTransaction(em -> userDAO.getUserByUsername(username, em)) == null;
    }

    public static boolean isEmailAvailable(String email) {
        UserDAO userDAO = new UserDAO();
        return Database.doInTransaction(em -> userDAO.getUserByEmail(email, em)) == null;
    }

    public static void updateUser(User user) {
        UserDAO userDAO = new UserDAO();
        Database.doInTransactionWithoutResult(em -> userDAO.update(user, em));
    }
}
