package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.CartDAO;
import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DAO.impl.UserDAO;
import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.CartItem;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.entities.User;

import java.util.Set;

public class CartServices {
    public static void addProductToCart(Cart cart, int productId, int quantity) {
        Database.doInTransaction(em -> {
            ProductDAO productDAO = new ProductDAO();

            Product product = productDAO.get(productId, em).orElseThrow(() -> new RuntimeException("Product not found"));

            Set<CartItem> cartItems = cart.getCartItems();

            CartItem cartItem = cartItems.stream()
                    .filter(ci -> ci.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElseGet(() -> {
                        CartItem newCartItem = new CartItem(product ,cart , (short) 0);
                        cart.addCartItem(newCartItem);
//                        cartItems.add(newCartItem);
                        return newCartItem;
                    });

            cartItem.setQuantity((short) (quantity));

            return null;
        });

    }


    public static void addProductToCartLogin(Cart cart, int productId, int quantity) {
        Database.doInTransaction(em -> {
            ProductDAO productDAO = new ProductDAO();

            Product product = productDAO.get(productId, em).orElseThrow(() -> new RuntimeException("Product not found"));

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

    public static void addOfflineCartItemsToUserCart(){};

    public static void updateCart(Cart cart) {
        Database.doInTransactionWithoutResult(em -> {
            CartDAO cartDAO = new CartDAO();
            cartDAO.update(cart, em);
        });
    }
}
