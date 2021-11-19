package com.healthtrack.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.entity.User;
import com.healthtrack.exception.DBException;
import com.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet(name = "user", urlPatterns = { "/user" })
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Logger logger = null;
    UserDAO userDAO = null;
    HttpSession session = null;

    @Override
    public void init() throws ServletException {
        super.init();
        logger = java.util.logging.Logger.getLogger(this.getClass().getName());
        userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
    }

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
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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

        request.getRequestDispatcher("/user-form.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        logger.info("Insert");
        try {
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
            User userRegistered = userDAO.getOne(registeredUserId);
            session = request.getSession();
            session.setAttribute("user", userRegistered);
            request.setAttribute("message", "Registro feito com sucesso");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("error", "Erro ao cadastrar");
        } catch (Exception error) {
            error.printStackTrace();
            request.setAttribute("error", "Erro, por favor valide os dados");
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
        request.getRequestDispatcher("/user-form.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        logger.info("update");
        try {
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
            userDAO.update(user);
            logger.info("Update Successfully");
            request.setAttribute("message", "Atualização feita com sucesso");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("error", "Erro ao editar");
        } catch (Exception error) {
            error.printStackTrace();
            request.setAttribute("error", "Erro, por favor valide os dados");
        }
        request.getRequestDispatcher("user-home.jsp").forward(request, response);
    }

    protected void userDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("delete");
        try {
            session = request.getSession();
            User user = (User) session.getAttribute("user");
            userDAO.delete(user.getId());
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("error", "Erro ao editar");
        } catch (Exception error) {
            error.printStackTrace();
            request.setAttribute("error", "Erro, por favor valide os dados");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}