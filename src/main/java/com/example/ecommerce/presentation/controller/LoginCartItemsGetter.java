package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.model.DTO.CartProductDto;
import com.example.ecommerce.model.DTO.LoggedInUserDto;
import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.CartItem;
import com.example.ecommerce.model.entities.Product;
import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.model.mappers.CategoryMapper;
import com.example.ecommerce.model.services.CartServices;
import com.example.ecommerce.model.services.UserServices;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginCartItemsGetter implements ServletResolverInt {
    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("currentUser")!=null)
            return doPost(request,response);
        return null;
    }

    private ViewResolver doPost(HttpServletRequest request, HttpServletResponse response) {
        String productIds1 =request.getParameter("productIds");
        String quantities1= request.getParameter("quantities");

        Gson gson = new Gson();
        Integer[] productIds =  gson.fromJson(productIds1,Integer[].class);
        Integer[] quantities = gson.fromJson(quantities1,Integer[].class);

        LoggedInUserDto loggedInUserDto = (LoggedInUserDto) request.getSession().getAttribute("currentUser");
        Optional<User> optionalUser = UserServices.getUser(loggedInUserDto.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Cart cart = user.getCart();
            System.out.println("i'm the user : \n"+user);
            for(int i=0;i<productIds.length;i++){
                CartServices.addProductToCartLogin(cart,productIds[i],quantities[i]);
            }
            CartServices.updateCart(cart);
            List<CartProductDto> redirectedCartItems = new ArrayList<>();
            System.out.println("size = "+cart.getCartItems().size());
            for(CartItem cartItem: cart.getCartItems()){
                Product product = cartItem.getProduct();
                System.out.println(product);
                redirectedCartItems.add(new CartProductDto(product.getId(),product.getName(),product.getPrice(),CategoryMapper.INSTANCE.toDto(product.getCategory()),product.getMainImageUrl(), cartItem.getQuantity()));
            }
            System.out.println("Alooooooooooooooo:   "+redirectedCartItems.size()+"\n\n\n");

            String json = gson.toJson(redirectedCartItems);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                response.getWriter().print(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ViewResolver viewResolver = new ViewResolver();
            viewResolver.sendOnlyResponse();
            return viewResolver;
        }

        return null;
    }
}
