package com.healthtrack.controller;

import java.io.IOException;
import java.util.logging.Logger;

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

import oracle.jdbc.logging.annotations.Logging;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Logger logger = null;
    private UserDAO userDao;
    private EmailBO bo;

    public LoginServlet() {
        userDao = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
        logger = java.util.logging.Logger.getLogger(this.getClass().getName());
        bo = new EmailBO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Log Out");

        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Log In");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.info("User id: " + email);
        logger.info("User id: " + password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        int id = userDao.validateUser(user);
        user = userDao.getOne(id);
        if (id > 0) {
            logger.info("Valid user");
            logger.info("User id: " + id);

            HttpSession session = request.getSession();
            session.setAttribute("user", id);
            request.setAttribute("user", user);
            String message = "Um login foi realizado";
            try {
                logger.info("Sending email");
                System.out.println("pass");
                // bo.sendEmail(email, "Login Realizado", message);
            } catch (Exception e) {
                logger.info("Error sending email");
                e.printStackTrace();
            }
        } else {
            logger.info("Error validating email");
            request.setAttribute("erro", "Usuário e/ou senha inválidos");
        }
        request.getRequestDispatcher("user-home.jsp").forward(request, response);
    }

}