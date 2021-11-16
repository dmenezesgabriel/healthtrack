package com.healthtrack.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet(name = "user", urlPatterns = { "/user" })
public class UserServlet extends HttpServlet {
    Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        logger.info("Requesting: " + action);

        try {
            switch (action) {
            case "new":
                logger.info("new");
                showNewForm(request, response);
                break;
            default:
                logger.info("default");
                showNewForm(request, response);
                break;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/new-user.jsp");
        dispatcher.forward(request, response);
    }
}