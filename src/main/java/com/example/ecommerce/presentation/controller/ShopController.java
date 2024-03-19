package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.services.CategoryServices;
import com.example.ecommerce.model.services.ProductServices;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.soap.SOAPElement;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ShopController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doGet(request , response);
    }

    public ViewResolver  doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String category = request.getParameter("category");

        if(category != null){
            Set<ProductDto> productDtos = new CategoryServices().getCategoryById(Integer.parseInt(category));

            Gson gson = new Gson();
            String json = gson.toJson(productDtos);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

            ViewResolver viewResolver = new ViewResolver();
            viewResolver.sendOnlyResponse();
            return viewResolver;
        }else {
            List<ProductDto> productDtos = new ProductServices().getProductsAll();
            request.setAttribute("products", productDtos);

//            Gson gson = new Gson();
//            String json = gson.toJson(productDtos);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(json);
//            System.out.println(json);
            ViewResolver viewResolver = new ViewResolver();
            viewResolver.forward(PAGES.SHOP.getValue());
            return viewResolver;

        }
    }
}
