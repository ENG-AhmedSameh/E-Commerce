package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.CartDAO;
import com.example.ecommerce.model.DAO.impl.OrderDAO;
import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DAO.impl.UserDAO;
import com.example.ecommerce.model.entities.*;

import java.math.BigDecimal;
import java.util.Set;

public class OrderSerVices {
    public static Order createOrder(User user) {
        Order order = new Order();

        order.setUser(user);
        //user.addorder()
        order.setCreateTime(java.time.Instant.now());

        try {
            Database.doInTransactionWithoutResult(em->{
                BigDecimal currentLimit = user.getCreditLimit();
                BigDecimal totalPrice = new BigDecimal(0);
                Cart cart = em.find(Cart.class, user.getCart().getId());
                 Set<CartItem> cartItems = cart.getCartItems();
                 for(CartItem cartItem: cartItems){
                     Product product = cartItem.getProduct();
                     short itemQuantity = cartItem.getQuantity();
                     Integer productQuantity = product.getAvailableQuantity();
                     if(productQuantity < itemQuantity){
                         throw new IllegalArgumentException("The order quantity of product " + product.getName() + " is more than the available quantity\n\n" +
                                 "Available quantity: " + productQuantity + "\n" +
                                 "Order quantity: " + cartItem.getQuantity());
                     }
                     if(currentLimit.compareTo(product.getPrice().multiply(new BigDecimal(itemQuantity))) < 0){
                         throw new IllegalArgumentException("The order price is more than the available credit limit\n" +
                                 "Available credit limit: " + user.getCreditLimit());
                     }

                     CartDAO cartDAO = new CartDAO();
                     cartDAO.removeCartItem(cartItem,em);

                     ProductDAO productDAO = new ProductDAO();
                     productDAO.updateProductQuantity(product,itemQuantity,em);


                     OrderItem orderItem = new OrderItem(order,product,product.getPrice(),itemQuantity);

                     order.addOrderItem(orderItem);
                     currentLimit = currentLimit.subtract(product.getPrice().multiply(new BigDecimal(itemQuantity)));
                     totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(itemQuantity)));
                 }
                 order.setPrice(totalPrice);
                 user.setCreditLimit(currentLimit);
                 OrderDAO orderDAO = new OrderDAO();
                 orderDAO.save(order,em);
                 //add order to user
                 UserDAO userDAO = new UserDAO();
                 userDAO.updateCreditLimit(user,currentLimit,em);
            });
         }catch (RuntimeException e){
             throw e;
        }
        return order;
    }
}
