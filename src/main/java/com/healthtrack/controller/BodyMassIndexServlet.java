package com.healthtrack.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthtrack.dao.BodyMassIndexDAO;
import com.healthtrack.dao.HeightDAO;
import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.WeightDAO;
import com.healthtrack.entity.BodyMassIndex;
import com.healthtrack.entity.Height;
import com.healthtrack.entity.User;
import com.healthtrack.entity.Weight;
import com.healthtrack.exception.DBException;
import com.healthtrack.factory.DAOFactory;

@WebServlet(name = "bmi", urlPatterns = { "/bmi" })
public class BodyMassIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Logger logger = null;
    UserDAO userDAO = null;
    HeightDAO heightDAO = null;
    WeightDAO weightDAO = null;
    BodyMassIndexDAO bodyMassIndexDAO = null;

    @Override
    public void init() throws ServletException {
        super.init();
        logger = java.util.logging.Logger.getLogger(this.getClass().getName());
        userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
        heightDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getHeightDAO());
        weightDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getWeightDAO());
        bodyMassIndexDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getBodyMassIndexDAO());
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
            case "edit":
                logger.info("edit");
                showEditForm(request, response);
                break;
            case "list":
                logger.info("list");
                list(request, response);
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
        String action = request.getParameter("action");
        logger.info("Requesting user action: " + action);

        try {
            switch (action) {
            case "create":
                logger.info("create");
                createBMI(request, response);
                break;
            case "delete":
                logger.info("delete");
                bmiDelete(request, response);
                break;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("New Form");
        request.getRequestDispatcher("/bmi-form-create.jsp").forward(request, response);
    }

    private void createBMI(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        logger.info("Create");
        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null) {
            userId = (int) session.getAttribute("user");

        }
        try {
            double heightValue = Double.parseDouble(request.getParameter("height"));
            double weightValue = Double.parseDouble(request.getParameter("weight"));
            DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate measureDate = LocalDate.parse(request.getParameter("measureDate"), f);

            User user = userDAO.getOne(userId);
            Height height = new Height();
            height.setMeasureDate(measureDate);
            height.setUser(user);
            height.setMeasureValue(heightValue);
            int heightId = heightDAO.register(height);
            Weight weight = new Weight();
            weight.setMeasureDate(measureDate);
            weight.setUser(user);
            weight.setMeasureValue(weightValue);
            BodyMassIndex bmi = new BodyMassIndex();
            int weightId = weightDAO.register(weight);
            // Retrieve registered objects
            height = heightDAO.getOne(heightId);
            weight = weightDAO.getOne(weightId);

            bmi.setHeight(height);
            bmi.setWeight(weight);
            bmi.setUser(user);
            bmi.setMeasureDate(measureDate);
            bmi.setMeasureValue(bmi.calculateIndex());
            // Register to database
            int registeredBMI = bodyMassIndexDAO.register(bmi);
            List<BodyMassIndex> list = bodyMassIndexDAO.getByUser(userId);
            request.setAttribute("bmis", list);
            request.setAttribute("message", "Registro feito com sucesso");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("error", "Erro ao cadastrar");
        } catch (Exception error) {
            error.printStackTrace();
            request.setAttribute("error", "Erro, por favor valide os dados");
        }
        request.getRequestDispatcher("/bmi-list.jsp").forward(request, response);

    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Edit Form");
        int id = Integer.parseInt(request.getParameter("id"));
        BodyMassIndex bmi = bodyMassIndexDAO.getOne(id);
        request.setAttribute("bmi", bmi);
        request.getRequestDispatcher("/bmi-form-update.jsp").forward(request, response);
    }

    protected void bmiDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("delete");
        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null) {
            userId = (int) session.getAttribute("user");

        }
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            bodyMassIndexDAO.delete(id);
            List<BodyMassIndex> list = bodyMassIndexDAO.getByUser(userId);
            request.setAttribute("bmis", list);
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("error", "Erro ao deletar");
        } catch (Exception error) {
            error.printStackTrace();
            request.setAttribute("error", "Erro, por favor valide os dados");
        }
        request.getRequestDispatcher("/bmi-list.jsp").forward(request, response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("List");
        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null) {
            userId = (int) session.getAttribute("user");

        }
        List<BodyMassIndex> list = bodyMassIndexDAO.getByUser(userId);
        request.setAttribute("bmis", list);

        request.getRequestDispatcher("/bmi-list.jsp").forward(request, response);
    }

}
