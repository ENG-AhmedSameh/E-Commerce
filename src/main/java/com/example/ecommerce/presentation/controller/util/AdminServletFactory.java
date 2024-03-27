package com.example.ecommerce.presentation.controller.util;

import com.example.ecommerce.presentation.controller.*;

import java.util.HashMap;
import java.util.Map;

public class AdminServletFactory {
    // class contents list of all controllers

        private static final Map<String, ServletResolverInt> controllers = new HashMap<>();

        static {
            // Initialize the controllers map here

             controllers.put("productsPanel", new ProductsPanelController());
             controllers.put("deleteProductsPanel", new DeleteProductsPanelController());
             controllers.put("customersPanel", new CustomersPanelController());
             controllers.put("ordersPanel", new OrdersPanelController());
             controllers.put("addProducts", new AddProducts());

        }

        // private constructor to prevent instantiation
        private AdminServletFactory() {
        }

        // method to get the controller
        public static ServletResolverInt getController(final String controllerName) {
            return controllers.get(controllerName);
        }
}

