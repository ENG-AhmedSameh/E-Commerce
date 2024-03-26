package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.LoggedInUserDto;
import com.example.ecommerce.model.entities.Order;
import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.model.mappers.LoggedInUserMapper;
import com.example.ecommerce.model.services.OrderSerVices;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class CheckOutController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doPost(request , response);
    }

    public ViewResolver  doPost (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        ViewResolver viewResolver = new ViewResolver();
        LoggedInUserDto loggedInUserDto = (LoggedInUserDto) request.getSession().getAttribute("currentUser");
        String responseMessage="";
        if(loggedInUserDto==null){
            responseMessage = "Error: you should register your account first !!";
        }else {
            Optional<User> optionalUser =UserServices.getUser(loggedInUserDto.getId());

            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                try {
                    Order order = OrderSerVices.createOrder(user);
                    request.getSession().setAttribute("currentUser",LoggedInUserMapper.INSTANCE.toDto(order.getUser()));
                    responseMessage="Order Created Successfully\n and will be shipped in two days";
                }catch (IllegalArgumentException e){
                    responseMessage = "Error:\n"+e.getMessage();
                }catch (RuntimeException e){

                    responseMessage = "Error";
                }
            }
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseMessage);


        viewResolver.sendOnlyResponse();
        return viewResolver;

    }
}
