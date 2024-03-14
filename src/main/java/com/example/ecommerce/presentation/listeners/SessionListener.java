package com.example.ecommerce.presentation.listeners;

import com.example.ecommerce.model.entities.Cart;
import com.example.ecommerce.model.entities.User;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated( HttpSessionEvent sessionEvent ) {
        System.out.println( "Session created with id " + sessionEvent.getSession().getId() );

    }

    @Override
    public void sessionDestroyed( HttpSessionEvent sessionEvent ) {
//        HttpSession session = sessionEvent.getSession();
//        User sessionUser = (User) session.getAttribute( "user" );
//        ShoppingCart sessionShoppingCart = (ShoppingCart) session.getAttribute( "shoppingCart" );
//
//        if ( sessionUser != null && sessionShoppingCart != null ) {
//            DomainFacade.persistShoppingCart( sessionShoppingCart );
//        }
    }
}
