package com.healthtrack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthtrack.bo.EmailBO;
import com.healthtrack.dao.UserDAO;
import com.healthtrack.entity.User;
import com.healthtrack.exception.EmailException;
import com.healthtrack.factory.DAOFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDao;
    private EmailBO bo;

    public LoginServlet() {
        userDao = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
        bo = new EmailBO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        if (userDao.validateUser(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", email);
            String message = "Um login foi realizado";
            try {
                bo.sendEmail(email, "Login Realizado", message);
            } catch (EmailException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("erro", "Usuário e/ou senha inválidos");
        }
        request.getRequestDispatcher("user-home.jsp").forward(request, response);
    }

}