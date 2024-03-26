package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.OrderDto;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.services.OrdersServices;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class OrdersPanelController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return doGet(request,response);
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) {
        List<OrderDto> orderDtos = new OrdersServices().getAllOrders();
        request.getSession().setAttribute("ordersPanel", orderDtos);

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.ORDERSPANEL.getValue());
        return viewResolver;
    }


}
