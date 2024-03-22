package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CartController implements ServletResolverInt {

    @Override
    public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
        // code to handle the request
       return doGett(request, response);
    }
    protected ViewResolver doGett(HttpServletRequest req, HttpServletResponse resp){
        // code to handle the request
        System.out.println("CartController doGet");
        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.SHOPPING_CART.getValue());
        return viewResolver;
    }

}
