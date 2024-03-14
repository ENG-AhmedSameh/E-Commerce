package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CheckUserNameAvailabilityController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean isAvailable = UserServices.isUserNameAvailable(username);
        resp.getWriter().write(isAvailable ? "valid" : "not valid");
    }
}
