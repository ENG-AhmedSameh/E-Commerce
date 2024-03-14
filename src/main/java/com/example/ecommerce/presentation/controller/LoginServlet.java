package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("currentUser") != null)
            resp.sendRedirect("index.html");
        resp.sendRedirect("login.html");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDto userDto = UserServices.loginUser(username, password);
        if (userDto != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("currentUser", userDto);
            resp.sendRedirect("index.html");
        } else {
            resp.sendRedirect("login.html");
        }
    }
}
