package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.LoggedInUserDto;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogOutController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doGet(request, response);
    }

    public ViewResolver doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggedInUserDto loggedInUser = (LoggedInUserDto) req.getSession().getAttribute("currentUser");
        ViewResolver viewResolver = new ViewResolver();
        if(loggedInUser != null) {
            req.getSession().invalidate();
            viewResolver.redirect("front?page=home");
            return viewResolver;

        }

        viewResolver.redirect(PAGES.LOGIN.getValue());
        return viewResolver;


    }
}
