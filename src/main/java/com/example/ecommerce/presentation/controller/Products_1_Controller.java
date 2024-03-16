package com.example.ecommerce.presentation.controller;


import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.ProductDAO;
import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.mappers.ProductMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class Products_1_Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //retrieve all products with category ---> id = 1 , from database
        List<Product> products_1 = Database.doInTransaction(em -> {
            return new ProductDAO().getAllProductsByCategoryId(1, em);
        });

        //each product entity will be retrieved from database , we wil  mapper it to dto
        //then collect the mapped ProductDto objects into a new list
//        List<ProductDto> productDtos = products_1.stream()
//                .map(ProductMapper.INSTANCE::toDto)
//                .collect(Collectors.toList());
        List<ProductDto>productDtos = ProductMapper.INSTANCE.toListDto(products_1);

        String json = toJson(productDtos);

        PrintWriter printWriter = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        printWriter.write(json);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static String toJson(List<ProductDto> productDtos) {
        Gson gson = new Gson();
        return gson.toJson(productDtos);
    }
}
