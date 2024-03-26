package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DAO.Database;
import com.example.ecommerce.model.DAO.impl.UserDAO;
import com.example.ecommerce.model.DTO.LoggedInUserDto;
import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.model.mappers.LoggedInUserMapper;
import com.example.ecommerce.model.mappers.UserMapper;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class UpdateProfile implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodType = request.getMethod();
        if ("GET".equals(methodType)) {
            System.out.println("i want to go to doGet of UpdateServlet.");
            return doGet(request,response);
        } else if ("POST".equals(methodType)) {
            return doPost(request,response);
        }
        return null;
    }

    private ViewResolver doGet(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        LoggedInUserDto user = (LoggedInUserDto) session.getAttribute("currentUser");

        if (user != null) {
            ViewResolver viewResolver = new ViewResolver();
            viewResolver.forward(PAGES.UPDATEPROFILE.getValue());
            return viewResolver;
        }
        return null;
    }

    private ViewResolver doPost(HttpServletRequest request, HttpServletResponse response) {

        String job = request.getParameter("job");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phoneNumber = request.getParameter("phone_number");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        BigDecimal creditLimit = new BigDecimal(request.getParameter("credit_limit"));

        HttpSession session = request.getSession(false);
        LoggedInUserDto userDto = (LoggedInUserDto) session.getAttribute("currentUser");
        Optional<User> userOptional = UserServices.getUser(userDto.getId());
        User user = userOptional.orElse(null);
        if (user != null) {
            user.setJob(job);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setCity(city);
            user.setStreet(street);
            user.setCreditLimit(creditLimit);
            UserServices.updateUser(user);
            session.setAttribute("currentUser", LoggedInUserMapper.INSTANCE.toDto(user));
        }

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.UPDATEPROFILE.getValue());
        return viewResolver;
    }

}
