package com.example.ecommerce.presentation.controller.util;

import com.example.ecommerce.presentation.controller.CheckUserNameAvailabilityController;
import com.example.ecommerce.presentation.controller.LoginController;
import com.example.ecommerce.presentation.controller.SignUpController;

import java.util.HashMap;
import java.util.Map;

public class ServletFactory {
    // class contents.. list of all controllers

        private static final Map<String, ServletResolverInt> controllers = new HashMap<>();

        static {
            // Initialize the controllers map here
             controllers.put("login", new LoginController());
             controllers.put("registration", new SignUpController());
             controllers.put("checkUserName",new CheckUserNameAvailabilityController());
        }

        // private constructor to prevent instantiation
        private ServletFactory() {
        }

        // method to get the controller
        public static ServletResolverInt getController(final String controllerName) {
            return controllers.get(controllerName);
        }
}

