package com.healthtrack.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.entity.User;
import com.healthtrack.factory.DAOFactory;

@WebServlet(name = "User", urlPatterns = { "/user" })
public class UserServlet {
    UserDAO userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUser(request, response);

            }
        } catch (SQLException error) {
            throw new ServletException(error);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"), f);
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // Set user information
        User user = new User();
        user.setName(name);
        user.setGender(gender);
        user.setBirthDate(birthDate);
        user.setEmail(email);
        user.setPassword(password);
        // Register to database
        int registeredUserId = userDAO.register(user);
        if (registeredUserId > 0) {
            request.setAttribute("user", user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } else {
            request.setAttribute("error", "Informação invalida");
        }
        // response.sendRedirect("user-home.jsp");
    }
}
