package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class checkEmailAvailabilityController implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)  {
            return doPost(request,response);

    }
    protected ViewResolver doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        boolean isAvailable = UserServices.isEmailAvailable(email);
        try {
            System.out.println("checkmail"+email);
            resp.getWriter().write(isAvailable ? "valid" : "not valid");
            ViewResolver viewResolver = new ViewResolver();
            viewResolver.sendOnlyResponse();
            return viewResolver;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
