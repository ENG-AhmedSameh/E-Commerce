package com.example.ecommerce.presentation.controller;

import com.example.ecommerce.presentation.controller.util.ServletFactory;
import com.example.ecommerce.presentation.controller.util.ServletResolverInt;
import com.example.ecommerce.presentation.controller.util.ViewResolver;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FrontController extends HttpServlet {

    private static final String CONTROLLER_NAME = "Page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        String controllerName = request.getParameter(CONTROLLER_NAME);
        System.out.println(" Controller is: " + controllerName);


        ServletResolverInt servletResolverInt = ServletFactory.getController(controllerName);
        ViewResolver resolver = servletResolverInt.resolve(request, response);
        dispatch(request, response, resolver);
    }

    private void dispatch(final HttpServletRequest request, final HttpServletResponse response,
                          final ViewResolver resolver) throws ServletException, IOException {

        String view = resolver.getView();
        switch (resolver.getResolveAction()) {
            case FORWARD:
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
                dispatcher.forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(view);
                break;

            default:
                break;
        }

    }



}
