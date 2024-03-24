package com.example.ecommerce.presentation.controller;



import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProductDetailsController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doGet(request, response);
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(request.getQueryString());
//        int id = Integer.parseInt(request.getParameter("id"));
//        ProductDto productDto = new ProductServices().getProductById(id);

//        request.setAttribute("product", productDto);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.PRODUCT_DETAILS.getValue());
        return viewResolver;
    }
}


