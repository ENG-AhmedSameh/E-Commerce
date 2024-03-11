package com.example.ecommerce.presentation.listeners;

import com.example.ecommerce.model.DAO.Database;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed( ServletContextEvent sce ) {
        System.out.println( "CLOSED " );
        Database.close();
    }
}
