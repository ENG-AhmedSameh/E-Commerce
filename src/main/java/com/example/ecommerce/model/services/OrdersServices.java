package com.example.ecommerce.model.services;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.OrderDAO;
import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DAO.impl.UserDAO;
import com.example.ecommerce.model.DTO.OrderDto;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.entities.Category;
import com.example.ecommerce.model.entities.Order;
import com.example.ecommerce.model.entities.OrderItem;
import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.model.mappers.OrderMapper;
import com.example.ecommerce.model.mappers.UserMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class OrdersServices {


    public List<OrderDto> getAllOrders() {
        try {
            return Database.doInTransaction(em -> {
                OrderDAO orderDAO = new OrderDAO();
                List<Order> orders = orderDAO.getAllOrders(em);
                return OrderMapper.INSTANCE.toListDto(orders);
            });
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            // Handle exception gracefully, perhaps by throwing a custom exception or returning an empty list
            return Collections.emptyList();
        }
    }



}
