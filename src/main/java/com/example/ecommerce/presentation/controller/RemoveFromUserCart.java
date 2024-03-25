package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.LoggedInUserDto;
import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.model.services.CartServices;
import com.example.ecommerce.model.services.ProductServices;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

//Sameh
public class RemoveFromUserCart  implements ServletResolverInt {

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doPost(request, response);
    }

    private ViewResolver doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoggedInUserDto loggedInUserDto = (LoggedInUserDto) request.getSession().getAttribute("currentUser");

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.sendOnlyResponse();
        if(loggedInUserDto != null ){

            Optional<User> optionalUser = UserServices.getUser(loggedInUserDto.getId());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                int productId = Integer.parseInt(request.getParameter("id"));
                Cart cart = user.getCart();
                CartServices.removeFromCart(cart, productId);
            }
        }
        return viewResolver;

    }

}
