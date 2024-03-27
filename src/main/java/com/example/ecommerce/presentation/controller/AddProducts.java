package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.entities.Category;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.entities.ProductImage;

import com.example.ecommerce.model.services.CartServices;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AddProducts implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodType = request.getMethod();
        if ("GET".equals(methodType)) {
            return doGet(request, response);
        } else if ("POST".equals(methodType)) {
            return doPost(request, response);
        }
        return null;
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) {

        List<ProductDto> productDtos = new ProductServices().getProductsAllExist();
        request.getSession().setAttribute("productsPanel", productDtos);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.PRODUCTSPANEL.getValue());
        return viewResolver;
    }

    private ViewResolver doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String jsonData = null;
        try {
            jsonData = request.getReader().lines().collect(Collectors.joining());

            String[] s = jsonData.split(":");
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode objNode = objectMapper.readTree(jsonData);

            Product productTobeAdded = new Product();

            //Very bad code here, but I will leave it as it is 3shan mafeesh w2t
            // Extract numbers from the JSON array
            productTobeAdded.setName(objNode.get("name").asText());
            productTobeAdded.setDescription(objNode.get("description").asText());
            productTobeAdded.setAvailableQuantity(objNode.get("availableQuantity").asInt());
            productTobeAdded.setPrice(BigDecimal.valueOf(objNode.get("price").asDouble()));
            productTobeAdded.setDiscountPercentage((byte) 0);
            productTobeAdded.setMainImageUrl(objNode.get("mainImageUrl").asText());
            productTobeAdded.setIsDeleted((byte)0);
            ProductImage productImage1 = new ProductImage(productTobeAdded , objNode.get("secondImageUrl").asText());
            ProductImage productImage2 = new ProductImage(productTobeAdded , objNode.get("thirdImageUrl").asText());
            Database.doInTransactionWithoutResult(em->{
                Category category = em.find(Category.class, objNode.get("categoryId").asInt());
                productTobeAdded.setCategory(category);
                productTobeAdded.addProductImage(productImage1);
                productTobeAdded.addProductImage(productImage2);
                em.persist(productTobeAdded);

            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //****************************************************************

        List<ProductDto> productDtos = new ProductServices().getProductsAllExist();
        //productDtos.add(new ProductMapperImpl().toDto(productTobeAdded));
        request.getSession().setAttribute("productsPanel", productDtos);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.PRODUCTSPANEL.getValue());
        return viewResolver;
    }
}