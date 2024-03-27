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

public class ShopMoreProductsController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doGet(request,response);
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) {
        int page =1;
        int pageSize = 10;
        if(request.getParameter("pNum") != null) {
            System.out.println("page number:" + request.getParameter("pNum"));
            page = Integer.parseInt(request.getParameter("pNum"));
        }

        int firstResult = page  * pageSize;

        List<ProductDto> products = ProductServices.getNextTenProducts(firstResult,pageSize);

        String json = new Gson().toJson(products);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ViewResolver viewResolver = new ViewResolver();
        viewResolver.sendOnlyResponse();
        return viewResolver;
    }



}
