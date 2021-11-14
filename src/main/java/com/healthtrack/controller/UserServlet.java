package com.healthtrack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.factory.DAOFactory;

@WebServlet(name = "User", urlPatterns = { "/user" })
public class UserServlet {
    UserDAO addressDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
