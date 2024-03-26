package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Category;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.services.ProductServices;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ProductsPanelController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodType = request.getMethod();
        if ("GET".equals(methodType)) {
            System.out.println("Now in , doGet in ProductsPanelController.");
            return doGet(request, response);
        } else if ("POST".equals(methodType)) {
            System.out.println("Now in , doPost in ProductsPanelController.");
            return doPost(request, response);
        }
        return null;
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) {
        List<ProductDto> productDtos = new ProductServices().getProductsAll();
        request.getSession().setAttribute("productsPanel", productDtos);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.PRODUCTSPANEL.getValue());
        return viewResolver;
    }

    private ViewResolver doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
//        String action = jsonObject.get("action").getAsString();
//        System.out.println("action = " + action);
        //**********************************************************
//        String action = request.getParameter("action");
//        System.out.println("action = " + action);
//
//        if ("delete".equals(action)) {
//            handleDelete(request, response);
//        }


        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        String jsonData = sb.toString();
        Gson gson = new Gson();
        ProductDto dataObject = gson.fromJson(jsonData, ProductDto.class);
        //****************************************************************
        System.out.println(dataObject.toString());
        System.out.println(dataObject.getName());
        System.out.println(dataObject.getId());
        //****************************************************************


        Product updatedProduct = new Product();
        updatedProduct.setId(dataObject.getId());
        updatedProduct.setName(dataObject.getName());
        updatedProduct.setDescription(dataObject.getDescription());
        updatedProduct.setAvailableQuantity(dataObject.getAvailableQuantity());
        updatedProduct.setPrice(dataObject.getPrice());
        updatedProduct.setDiscountPercentage(dataObject.getDiscountPercentage());
        updatedProduct.setMainImageUrl(dataObject.getMainImageUrl());

        Category category = new Category();
        category.setName(dataObject.getCategory().getName());
        updatedProduct.setCategory(category);

        System.out.println(updatedProduct.getName());

        new ProductServices().updateProduct(updatedProduct);

        List<ProductDto> productDtos = new ProductServices().getProductsAll();
        System.out.println("after updateProduct method  = " + productDtos.get(0).getName());
        request.getSession().setAttribute("productsPanel", productDtos);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.PRODUCTSPANEL.getValue());
        return viewResolver;

        }

//    private void handleDelete(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("handleDelete method");
//    }




}

