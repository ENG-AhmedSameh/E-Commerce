package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

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

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            user = new User();
            user.setUserName("Gogz");
            user.setEmail("hajar@gmail.com");
            user.setJob("Software Engineer");
            user.setFirstName("hajar");
            user.setLastName("Ramadan");
            user.setCity("Benha");
            user.setPhoneNumber("01033729354");
            user.setStreet("13 Farid Nada");
            user.setCreditLimit(BigDecimal.valueOf(1000));

            session.setAttribute("currentUser", user);
            System.out.println("user = "+user.getUserName());
        }

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.UPDATEPROFILE.getValue());
        return viewResolver;

    }

    private ViewResolver doPost(HttpServletRequest request, HttpServletResponse response) {

        String job = request.getParameter("job");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phoneNumber = request.getParameter("phone_number");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        BigDecimal creditLimit = new BigDecimal(request.getParameter("credit_limit"));

        HttpSession session = request.getSession(false);//just retrieve the current session
        User user = (User) session.getAttribute("currentUser");

        if (user != null) {
            user.setJob(job);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setCity(city);
            user.setStreet(street);
            user.setCreditLimit(creditLimit);
            session.setAttribute("currentUser", user);//update currentUser
            System.out.println("user = " + user.getUserName());
        }

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.forward(PAGES.UPDATEPROFILE.getValue());
        return viewResolver;
    }

}
