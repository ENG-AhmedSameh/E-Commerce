package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.CartDto;
import com.example.ecommerce.model.DTO.LoggedInUserDto;
import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.CartItem;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.model.mappers.CartMapper;
import com.example.ecommerce.model.services.CartServices;
import com.example.ecommerce.model.services.ProductServices;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AddToUserCart implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) {
        // code to handle the request
        return doPost(request, response);
    }
    protected ViewResolver doPost(HttpServletRequest req, HttpServletResponse resp){
        // code to handle the request
        System.out.println("AddToUserCart Post");
        resp.setContentType("application/json");
        LoggedInUserDto loggedInUserDto = (LoggedInUserDto) req.getSession().getAttribute("currentUser");
        System.out.println("The user id is: "+loggedInUserDto.getId());
        Optional<User> optionalUser = UserServices.getUser(loggedInUserDto.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Cart cart = user.getCart();
            System.out.println("cart: " + cart);
            if (cart != null) {
                String jsonData = null;
                try {
                    jsonData = req.getReader().lines().collect(Collectors.joining());
                    System.out.println("jsonData: " + jsonData);

                    String[] s = jsonData.split(":");
                    ObjectMapper objectMapper = new ObjectMapper();

                    JsonNode jsonArray = objectMapper.readTree(jsonData);

                    // Extract numbers from the JSON array
                    for (JsonNode objNode : jsonArray) {
                        int id = objNode.get("id").asInt();
                        int quantity = objNode.get("quantity").asInt();
                        System.out.println("id: " + id + " quantity: " + quantity);
                        System.out.println("---------------------------------------");

                        CartServices.addProductToCart(cart, id, quantity);
                    }
                    CartServices.updateCart(cart);
                    System.out.println(cart.getCartItems().size());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("jsonData: " + jsonData);
                System.out.println("cart updated");
                req.getSession().setAttribute("cart", CartMapper.INSTANCE.toDto(cart));
            }
        }
        ViewResolver viewResolver = new ViewResolver();
        viewResolver.sendOnlyResponse();
        return viewResolver;
    }
}
