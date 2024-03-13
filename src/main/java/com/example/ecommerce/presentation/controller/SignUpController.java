package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.AddressDto;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;


public class SignUpController extends HttpServlet {

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
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phoneNumber");
        BigDecimal creditLimit = new BigDecimal(req.getParameter("creditLimit"));
        String job = req.getParameter("job");
        String gender = req.getParameter("gender");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String buildingNumber = req.getParameter("buildingNumber");
        String floorNumber = req.getParameter("floorNumber");
        AddressDto addressDto = new AddressDto(null, city,street,buildingNumber,Byte.parseByte(floorNumber));
        UserDto userDto = new UserDto(null,username,password,firstname,lastname,email,phone,creditLimit,job,gender,null,addressDto,null,null,null);

        userDto = UserServices.registerNewUser(userDto);
        if (userDto != null) {
            resp.sendRedirect("index.html");
        } else {
            resp.sendRedirect("register.html");
        }
    }
}
