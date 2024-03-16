package com.example.ecommerce.presentation.controller.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ServletResolverInt {
    ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException;
}
