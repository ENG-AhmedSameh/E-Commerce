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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
        int pash =10;

        String pageParam = request.getParameter("page");
        int start;
        if (pageParam == null) {
            start = 0;
        } else {
            try {
                start = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                start = 0;
            }
        }
        if(start<0) {start=0;}

        List<ProductDto> productDtos = new ProductServices().getTenProducts(start*pash);
        request.getSession().setAttribute("productsPanel", productDtos);
        request.getSession().setAttribute("page", start);
        System.out.println("page = " + start+1);
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


//        StringBuilder sb = new StringBuilder();
//        BufferedReader reader = request.getReader();
//        try {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line).append('\n');
//            }
//        } finally {
//            reader.close();
//        }
//
//        String jsonData = sb.toString();
//
//        System.out.println("-------------------- json data --------------------");
//        System.out.println(jsonData);
//        System.out.println("---------------------------------------------------");
//
//        Gson gson = new Gson();
//        ProductDto dataObject = gson.fromJson(jsonData, ProductDto.class);
//        //****************************************************************
//        System.out.println(dataObject.toString());
//        System.out.println(dataObject.getName());
//        System.out.println(dataObject.getId());
        //****************************************************************

//
//        Product updatedProduct = new Product();
//
//        updatedProduct.setId(dataObject.getId());
//        updatedProduct.setName(dataObject.getName());
//        updatedProduct.setDescription(dataObject.getDescription());
//        updatedProduct.setAvailableQuantity(dataObject.getAvailableQuantity());
//        updatedProduct.setPrice(dataObject.getPrice());
//        updatedProduct.setDiscountPercentage(dataObject.getDiscountPercentage());
//        updatedProduct.setMainImageUrl(dataObject.getMainImageUrl());
//
//        Category category = new Category();
//        category.setName(dataObject.getCategory().getName());
//        updatedProduct.setCategory(category);

//        System.out.println(updatedProduct.getName());
//        System.out.println(updatedProduct.toString());
        //**************************************************************
        String jsonData = null;
        try {
            jsonData = request.getReader().lines().collect(Collectors.joining());

            String[] s = jsonData.split(":");
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode objNode = objectMapper.readTree(jsonData);


            // Extract numbers from the JSON array

            Database.doInTransactionWithoutResult(em->{
                Product productTobeUpdated = em.find(Product.class,objNode.get("id").asInt());
                productTobeUpdated.setName(objNode.get("name").asText());
                productTobeUpdated.setDescription(objNode.get("description").asText());
                productTobeUpdated.setAvailableQuantity(objNode.get("availableQuantity").asInt());
                productTobeUpdated.setPrice(BigDecimal.valueOf(objNode.get("price").asDouble()));
                productTobeUpdated.setDiscountPercentage((byte) 0);
                productTobeUpdated.setMainImageUrl(objNode.get("mainImageUrl").asText());
                productTobeUpdated.setIsDeleted((byte)0);
                ProductImage productImage1 = null;
                ProductImage productImage2 = null;
                boolean isFirstPhoto = true;
                for(ProductImage productImage:productTobeUpdated.getProductImages()){
                    if(isFirstPhoto){
                        productImage1 = em.find(ProductImage.class,productImage.getId());
                        productImage1.getId().setImageUrl(objNode.get("secondImageUrl").asText());
                        isFirstPhoto = false;
                    }else {
                        productImage2 = em.find(ProductImage.class,productImage.getId());
                        productImage2.getId().setImageUrl(objNode.get("secondImageUrl").asText());
                    }
                }
                em.merge(productImage1);
                em.merge(productImage2);
                productTobeUpdated.addProductImage(productImage1);
                productTobeUpdated.addProductImage(productImage2);
                new ProductServices().updateProduct(productTobeUpdated);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }




        List<ProductDto> productDtos = new ProductServices().getProductsAllExist();
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

