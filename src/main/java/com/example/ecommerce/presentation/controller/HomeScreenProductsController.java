package com.example.ecommerce.presentation.controller;


import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.services.ProductServices;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class HomeScreenProductsController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doGet(request, response);
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ProductDto> productDtos = new ProductServices().getFirstTenProducts();
        System.out.println(productDtos.size());
        String json = new Gson().toJson(productDtos);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.sendOnlyResponse();
        return viewResolver;
    }
}


