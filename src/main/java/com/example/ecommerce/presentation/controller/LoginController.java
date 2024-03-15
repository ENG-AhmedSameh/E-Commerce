package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController implements ServletResolverInt {
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
        System.out.println("LoginServlet doGet");
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("currentUser") != null)
            viewResolver.redirect(PAGES.HOME.getValue());
        else
            viewResolver.forward(PAGES.LOGIN.getValue());

        return viewResolver;
    }

    private ViewResolver doPost(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("login doPost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username: " + username + " password: " + password);
        ViewResolver viewResolver = new ViewResolver();
        UserDto userDto = UserServices.loginUser(username, password);
        if (userDto != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("currentUser", userDto);
            viewResolver.redirect(PAGES.HOME.getValue());
        } else {
            viewResolver.forward(PAGES.LOGIN.getValue());
        }
        return viewResolver;
    }
}
