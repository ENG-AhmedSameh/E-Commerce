package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) {
        String methodType = request.getMethod();
        if ("GET".equals(methodType)) {
            return doGet(request,response);
        } else if ("POST".equals(methodType)) {
            return doPost(request,response);
        }
        return null;
    }

    private ViewResolver doGet(HttpServletRequest req, HttpServletResponse resp) {
        ViewResolver viewResolver = new ViewResolver();
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("currentUser") != null)
            viewResolver.redirect("/index.html");
        else
            viewResolver.redirect("/pages/login-register.jsp");

        return viewResolver;
    }

    private ViewResolver doPost(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ViewResolver viewResolver = new ViewResolver();
        UserDto userDto = UserServices.loginUser(username, password);
        if (userDto != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("currentUser", userDto);
            viewResolver.redirect("/index.html");
        } else {
            viewResolver.redirect("/pages/login-register.jsp");
        }
        return viewResolver;
    }
}
