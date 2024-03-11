package com.example.ecommerce.presentation.controller.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ServletResolverInt {
    ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response);
}
