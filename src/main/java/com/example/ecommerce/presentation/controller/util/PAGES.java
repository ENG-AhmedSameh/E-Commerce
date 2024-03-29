package com.example.ecommerce.presentation.controller.util;

public enum PAGES {

    HOME("home", "/index.jsp"),
    LOGIN("login", "/pages/login-register.jsp"),
    SHOP("shop", "/pages/product.jsp"),
    PRODUCT("product", "/pages/product.jsp"),
    CART("cart", "/pages/cart.jsp"),
    PRODUCT_DETAILS("productDetails", "/pages/product-detail.jsp"),
    SHOPPING_CART("shoppingCart", "/pages/shopping-cart.jsp"),
    BLOG("blog", "/pages/blog.jsp"),
    BLOG_DETAILS("blogDetails", "/pages/blog-details.jsp"),
    CONTACT("contact", "/pages/contact.jsp"),
    ABOUT("about", "/pages/about.jsp"),
    UPDATEPROFILE("updateProfile","/pages/updateProfile.jsp"),
    PRODUCTSPANEL("productsPanel","/pages/productsPanel.jsp"),
    CUSTOMERSPANEL("customersPanel","/pages/customersPanel.jsp"),
    ADMIN_HOME_SCREEN("adminHome", "/pages/admin.jsp"),
    ORDERSPANEL("ordersPanel","/pages/ordersPanel.jsp");

    private final String key;
    private final String value;

    PAGES(String key, String value) {
        this.key = key;
        this.value = value;
    }



    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


}
