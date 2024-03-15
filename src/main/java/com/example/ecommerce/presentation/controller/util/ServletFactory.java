package com.example.ecommerce.presentation.controller.util;

import com.example.ecommerce.presentation.controller.LoginServlet;

import java.util.HashMap;
import java.util.Map;

public class ServletFactory {
    // class contents.. list of all controllers

        private static final Map<String, ServletResolverInt> controllers = new HashMap<>();

        static {
            // Initialize the controllers map here
             controllers.put("Login", new LoginServlet());
        }

        // private constructor to prevent instantiation
        private ServletFactory() {
        }

        // method to get the controller
        public static ServletResolverInt getController(final String controllerName) {
            return controllers.get(controllerName);
        }
}

