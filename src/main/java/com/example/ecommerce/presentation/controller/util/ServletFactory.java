package com.example.ecommerce.presentation.controller.util;

import com.example.ecommerce.presentation.controller.*;

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
             controllers.put("checkEmail",new checkEmailAvailabilityController());
             controllers.put("home", new HomeController());
             controllers.put("homeProducts",new HomeScreenProductsController());
             controllers.put("productImages",new ProductImagesController());
        }

        // private constructor to prevent instantiation
        private ServletFactory() {
        }

        // method to get the controller
        public static ServletResolverInt getController(final String controllerName) {
            return controllers.get(controllerName);
        }
}

