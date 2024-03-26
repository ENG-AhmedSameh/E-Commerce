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

public class DeleteProductsPanelController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doPost(request,response);
    }

    private ViewResolver doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
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

        System.out.println(updatedProduct.getName());

        new ProductServices().deleteProduct(updatedProduct);
        
        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.PRODUCTSPANEL.getValue());
        return viewResolver;

    }
}
