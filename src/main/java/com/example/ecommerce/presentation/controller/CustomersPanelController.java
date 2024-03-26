package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.ProductDto;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.services.ProductServices;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class CustomersPanelController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doGet(request,response);
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) {

        List<UserDto> userDtos = new UserServices().getAllUsers();
        request.getSession().setAttribute("customersPanel", userDtos);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.CUSTOMERSPANEL.getValue());
        return viewResolver;

    }


}
