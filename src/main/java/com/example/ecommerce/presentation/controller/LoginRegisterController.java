package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginRegisterController implements ServletResolverInt {
    // class contents
    private static final String NEWS_JSP = "/WEB-INF/pages/login-register.jsp";
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) {
        // method contents
        ViewResolver resolver = new ViewResolver();
        resolver.redirect(NEWS_JSP);
        return resolver;

    }

}
