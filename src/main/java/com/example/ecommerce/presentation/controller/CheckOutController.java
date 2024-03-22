package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckOutController implements ServletResolverInt {

        @Override
        public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) {
            return null;
        }
}
