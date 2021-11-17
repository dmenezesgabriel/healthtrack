package com.healthtrack.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.entity.User;
import com.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet(name = "user", urlPatterns = { "/user" })
public class UserServlet extends HttpServlet {
    Logger logger = null;
    UserDAO userDAO = null;
    HttpSession session = null;
    RequestDispatcher dispatcher = null;

    @Override
    public void init() throws ServletException {
        super.init();
        logger = java.util.logging.Logger.getLogger(this.getClass().getName());
        userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
    }

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        logger.info("Requesting user action: " + action);

        try {
            switch (action) {
            case "new":
                logger.info("new");
                showNewForm(request, response);
                break;
            case "insert":
                logger.info("insert");
                insertUser(request, response);
                break;
            case "edit":
                logger.info("edit");
                showEditForm(request, response);
                break;
            case "update":
                logger.info("update");
                updateUser(request, response);
                break;
            case "delete":
                logger.info("delete");
                userDelete(request, response);
                break;
            default:
                logger.info("default");
                dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
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
        logger.info("New Form");
        request.setAttribute("title", "Cadastro");
        request.setAttribute("action", "insert");
        request.setAttribute("button", "Cadastrar agora");
        request.setAttribute("formClass", "needs-validation");
        request.setAttribute("controlClass", "has-validation");

        dispatcher = getServletContext().getRequestDispatcher("/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        logger.info("Insert");
        String name = request.getParameter("name");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"), f);
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
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
            User userRegistered = userDAO.getOne(registeredUserId);
            session = request.getSession();
            session.setAttribute("user", userRegistered);
            request.setAttribute("message", "Registro feito com sucesso");
        } else {
            request.setAttribute("error", "Informação invalida");
        }
        request.getRequestDispatcher("user-home.jsp").forward(request, response);

    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Edit Form");
        request.setAttribute("title", "Editar");
        request.setAttribute("action", "update");
        request.setAttribute("button", "Salvar");
        request.setAttribute("formClass", "");
        request.setAttribute("controlClass", "");

        dispatcher = getServletContext().getRequestDispatcher("/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        logger.info("update");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"), f);
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        // Set user information
        User user = (User) session.getAttribute("user");
        user.setName(name);
        user.setGender(gender);
        user.setBirthDate(birthDate);
        user.setEmail(email);
        user.setPassword(password);
        // Register to database
        if (userDAO.update(user)) {
            logger.info("Update Successfully");
            request.setAttribute("message", "Atualização feita com sucesso");

        } else {
            request.setAttribute("error", "Informação invalida");
        }
        request.getRequestDispatcher("user-home.jsp").forward(request, response);
    }

    protected void userDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("delete");
        session = request.getSession();
        User user = (User) session.getAttribute("user");
        userDAO.delete(user.getId());
        dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}