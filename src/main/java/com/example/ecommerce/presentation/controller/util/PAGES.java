package com.example.ecommerce.presentation.controller.util;

public enum PAGES {

    HOME("home", "index.html"),
    LOGIN("login", "/pages/login-register.jsp");

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
