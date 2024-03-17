package com.example.ecommerce.presentation.controller;


import com.example.ecommerce.model.DAO.Database;
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

        List<ProductDto> productDtos = Database.doInTransaction(em -> {
            return new ProductServices().getFirstTenProducts(em);
        });

        System.out.println(productDtos.size());
        String json = new Gson().toJson(productDtos);

        System.out.println(json.length());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
        System.out.println("HomeScreenProductsController doGet() called");

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.sendOnlyResponse();
        return viewResolver;
    }
}

//    protected void doGet(HttpServletRequest request, HttpServletResponse response){
//
//        //retrieve all products with category ---> id = 1 , from database
//        List<Product> products_1 = Database.doInTransaction(em -> {
//            return new ProductDAO().getAllProductsByCategoryId(1, em);
//        });
//
//
//        List<ProductDto>productDtos = ProductMapper.INSTANCE.toListDto(products_1);
//
////        String json = toJson(productDtos);
////
////        PrintWriter printWriter = response.getWriter();
////
////        response.setContentType("application/json");
////        response.setCharacterEncoding("UTF-8");
////
////        printWriter.write(json);
//    }
//
////
////    public static String toJson(List<ProductDto> productDtos) {
////        Gson gson = new Gson();
////        return gson.toJson(productDtos);
//
////    }
