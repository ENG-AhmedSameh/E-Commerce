package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DAO.impl.UserDAO;
import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.CartItem;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.entities.User;

import java.util.Set;

public class CartServices {
    public static void addProductToCart(int userId, int productId, int quantity) {
        Database.doInTransaction(em -> {
            UserDAO userDAO = new UserDAO();
            ProductDAO productDAO = new ProductDAO();

            User user = userDAO.get(userId, em).orElseThrow(() -> new RuntimeException("User not found"));
            Product product = productDAO.get(productId, em).orElseThrow(() -> new RuntimeException("Product not found"));

            Cart cart = user.getCart();
            Set<CartItem> cartItems = cart.getCartItems();

            CartItem cartItem = cartItems.stream()
                    .filter(ci -> ci.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElseGet(() -> {
                        CartItem newCartItem = new CartItem(product ,cart , (short) 0);
                        cartItems.add(newCartItem);
                        return newCartItem;
                    });

            cartItem.setQuantity((short) (quantity));

            return null;
        });
    }


    public static void addProductToCartLogin(int userId, int productId, int quantity) {
        Database.doInTransaction(em -> {
            UserDAO userDAO = new UserDAO();
            ProductDAO productDAO = new ProductDAO();

            User user = userDAO.get(userId, em).orElseThrow(() -> new RuntimeException("User not found"));
            Product product = productDAO.get(productId, em).orElseThrow(() -> new RuntimeException("Product not found"));

            Cart cart = user.getCart();
            Set<CartItem> cartItems = cart.getCartItems();

            CartItem cartItem = cartItems.stream()
                    .filter(ci -> ci.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElseGet(() -> {
                        CartItem newCartItem = new CartItem(product ,cart , (short) 0);
                        cartItems.add(newCartItem);
                        return newCartItem;
                    });

            cartItem.setQuantity((short) (cartItem.getQuantity() + quantity));

            return null;
        });
    }

}
