package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doGet(request, response);
    }

    public ViewResolver doGet(HttpServletRequest req, HttpServletResponse resp) {
        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.HOME.getValue());
        return viewResolver;
    }
}
