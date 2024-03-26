package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Category;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.entities.ProductImage;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddProducts implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodType = request.getMethod();
        if ("GET".equals(methodType)) {
            System.out.println("Now in , doGet in AddProducts Controller.");
            return doGet(request, response);
        } else if ("POST".equals(methodType)) {
            System.out.println("Now in , doPost in AddProducts Controller.");
            return doPost(request, response);
        }
        return null;
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("hello 1 from , doGet in AddProducts Controller.");

        List<ProductDto> productDtos = new ProductServices().getProductsAllExist();
        request.getSession().setAttribute("productsPanel", productDtos);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.PRODUCTSPANEL.getValue());
        return viewResolver;
    }

    private ViewResolver doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("hello 1 from , doPost in AddProducts Controller.");

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

        System.out.println("hello 2 from , doPost in AddProducts Controller.");
        String jsonData = sb.toString();

        System.out.println("-------------------- json data --------------------");
        System.out.println(jsonData);
        System.out.println("---------------------------------------------------");

        Gson gson = new Gson();
        System.out.println("hello 3 from , doPost in AddProducts Controller.");
        ProductDto dataObject = gson.fromJson(jsonData, ProductDto.class);
        System.out.println("hello 4 from , doPost in AddProducts Controller.");
        //****************************************************************
        System.out.println(dataObject.toString());
        System.out.println(dataObject.getName());
        System.out.println(dataObject.getId());
        //****************************************************************


        Product addProduct = new Product();
//        addProduct.setId(dataObject.getId());
        addProduct.setId(100);
        addProduct.setName(dataObject.getName());
        addProduct.setDescription(dataObject.getDescription());
        addProduct.setAvailableQuantity(dataObject.getAvailableQuantity());
        addProduct.setPrice(dataObject.getPrice());
        addProduct.setDiscountPercentage(dataObject.getDiscountPercentage());
        addProduct.setMainImageUrl(dataObject.getMainImageUrl());


        Database.doInTransactionWithoutResult(em->{
            Category category = em.find(Category.class, dataObject.getCategory().getId());
            addProduct.setCategory(category);

            ProductImage productImage1 = new ProductImage(addProduct , secondImageUrl );
            ProductImage productImage2 = new ProductImage(addProduct , theardImageUrl );

            addProduct.addProductImage(productImage1);
            addProduct.addProductImage(productImage2);

            em.persist(addProduct);

        });
        //------------------------------------------------------



        //------------------------------------------------------

        System.out.println(addProduct.getName());
        System.out.println(addProduct.toString());
        System.out.println("hello 5 from , doPost in AddProducts Controller.");

        //new ProductServices().updateProduct(updatedProduct);

        List<ProductDto> productDtos = new ProductServices().getProductsAllExist();
        System.out.println("hello 6 from , doPost in AddProducts Controller.");
        //productDtos.add(new ProductMapperImpl().toDto(addProduct));
        request.getSession().setAttribute("productsPanel", productDtos);
        System.out.println("hello 7 from , doPost in AddProducts Controller.");

        ViewResolver viewResolver = new ViewResolver();
        System.out.println("hello 8 from , doPost in AddProducts Controller.");
        viewResolver.forward(PAGES.PRODUCTSPANEL.getValue());
        System.out.println("hello 9 from , doPost in AddProducts Controller.");
        return viewResolver;
    }
}
