package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class checkEmailAvailability extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        boolean isAvailable = UserServices.isEmailAvailable(email);
        resp.getWriter().write(isAvailable ? "valid" : "not valid");
    }
}
