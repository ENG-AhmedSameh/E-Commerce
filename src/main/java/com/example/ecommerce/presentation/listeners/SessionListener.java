package com.example.ecommerce.presentation.listeners;

import com.example.ecommerce.model.DTO.CartDto;
import com.example.ecommerce.model.DTO.UserDto;
import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.User;
import com.example.ecommerce.model.mappers.CartMapper;
import com.example.ecommerce.model.mappers.UserMapper;
import com.example.ecommerce.model.services.UserServices;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.HashSet;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated( HttpSessionEvent sessionEvent ) {
        System.out.println( "Session created with id " + sessionEvent.getSession().getId() );
        CartDto cart = new CartDto(null,null,new HashSet<>());
        HttpSession session = sessionEvent.getSession();
        session.setAttribute( "cart", cart);
    }

    @Override
    public void sessionDestroyed( HttpSessionEvent sessionEvent ) {
        HttpSession session = sessionEvent.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute( "currentUser" );
        CartDto sessionShoppingCart = (CartDto) session.getAttribute( "cart" );
        if ( sessionUser != null && sessionShoppingCart != null ) {
            User user= UserMapper.INSTANCE.toEntity(sessionUser);
            Cart cart = CartMapper.INSTANCE.toEntity(sessionShoppingCart);
            user.getCart().getCartItems().addAll(cart.getCartItems());
            UserServices.updateUser(user);
        }
    }
}
