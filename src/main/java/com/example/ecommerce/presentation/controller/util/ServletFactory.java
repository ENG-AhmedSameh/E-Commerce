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
             controllers.put("shop",new ShopController());
             controllers.put("updateProfile", new UpdateProfile());
             controllers.put(("ShoppingCart"),new CartController());
             controllers.put("productsPanel", new ProductsPanelController());
             controllers.put("deleteProductsPanel", new DeleteProductsPanelController());
             controllers.put("customersPanel", new CustomersPanelController());


             controllers.put("logout",new LogOutController());
             controllers.put("productDetails",new ProductDetailsController());
             controllers.put("productDetailsData",new ProductDetailsDataController());
             controllers.put("AddToCart",new AddToUserCart());
             controllers.put("getUserCartItem", new LoginCartItemsGetter());
        }

        // private constructor to prevent instantiation
        private ServletFactory() {
        }

        // method to get the controller
        public static ServletResolverInt getController(final String controllerName) {
            return controllers.get(controllerName);
        }
}

