package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.CartDto;
import com.example.ecommerce.model.DTO.CartItemDto;
import com.example.ecommerce.model.DTO.LoggedInUserDto;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.mappers.LoggedInUserMapper;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.PAGES;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.HashSet;


public class SignUpController implements ServletResolverInt {

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) {
            return doPost(request,response);
    }


    protected ViewResolver doPost(HttpServletRequest req, HttpServletResponse resp) {
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

        UserDto userDto = new UserDto(null, username, password, firstname, lastname, phone, email, creditLimit, job, gender, city, street, null, null, null, null);

        System.out.println("username: " + username );
        ViewResolver viewResolver = new ViewResolver();
        try {
            LoggedInUserDto loggedInUser = UserServices.registerNewUser(userDto);

            if (loggedInUser != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("currentUser", loggedInUser);
                req.getSession().setAttribute("loginSuccess", true);
//           viewResolver.redirect(PAGES.HOME.getValue());
                viewResolver.redirect("front?page=home");

            } else {
                req.setAttribute("register-error", "Please Try Again");
                viewResolver.forward(PAGES.LOGIN.getValue());
            }
        }catch (Exception e){
            //e.printStackTrace();
            req.setAttribute("register-error", "Can't register user with these data !! Please Try Again");
            viewResolver.forward(PAGES.LOGIN.getValue());
        }
        return viewResolver;
    }
}
