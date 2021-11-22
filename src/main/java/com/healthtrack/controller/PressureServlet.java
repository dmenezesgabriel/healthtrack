package com.healthtrack.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.healthtrack.dao.PressureDAO;
import com.healthtrack.dao.HeightDAO;
import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.WeightDAO;
import com.healthtrack.entity.Pressure;
import com.healthtrack.entity.Height;
import com.healthtrack.entity.User;
import com.healthtrack.entity.Weight;
import com.healthtrack.exception.DBException;
import com.healthtrack.factory.DAOFactory;

@WebServlet(name = "pressure", urlPatterns = { "/pressure" })
public class PressureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = null;

    Logger logger = null;
    UserDAO userDAO = null;
    PressureDAO pressureDAO = null;

    @Override
    public void init() throws ServletException {
        super.init();
        logger = java.util.logging.Logger.getLogger(this.getClass().getName());
        userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
        pressureDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getPressureDAO());
        gson = new Gson();
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
            case "overtime":
                logger.info("overtime");
                overTime(request, response);
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
                createPRESSURE(request, response);
                break;
            case "update":
                logger.info("update");
                updatePRESSURE(request, response);
                break;
            case "delete":
                logger.info("delete");
                pressureDelete(request, response);
                break;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("New Form");
        request.getRequestDispatcher("/pressure-form-create.jsp").forward(request, response);
    }

    private void createPRESSURE(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        logger.info("Create");
        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null) {
            userId = (int) session.getAttribute("user");

        }
        try {
            int systolicPressureValue = Integer.parseInt(request.getParameter("systolicPressure"));
            int diastolicPressureValue = Integer.parseInt(request.getParameter("diastolicPressure"));
            DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate measureDate = LocalDate.parse(request.getParameter("measureDate"), f);

            User user = userDAO.getOne(userId);
            Pressure pressure = new Pressure();

            pressure.setUser(user);
            pressure.setMeasureDate(measureDate);
            pressure.setDiastolicPressureValue(diastolicPressureValue);
            pressure.setSystolicPressureValue(systolicPressureValue);
            // Register to database
            int registeredPressure = pressureDAO.register(pressure);
            List<Pressure> list = pressureDAO.getByUser(userId);
            request.setAttribute("pressures", list);
            request.setAttribute("message", "Registro feito com sucesso");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("error", "Erro ao cadastrar");
        } catch (Exception error) {
            error.printStackTrace();
            request.setAttribute("error", "Erro, por favor valide os dados");
        }
        request.getRequestDispatcher("/pressure-list.jsp").forward(request, response);

    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Edit Form");
        int id = Integer.parseInt(request.getParameter("id"));
        Pressure pressure = pressureDAO.getOne(id);
        request.setAttribute("pressure", pressure);
        request.getRequestDispatcher("/pressure-form-update.jsp").forward(request, response);
    }

    private void updatePRESSURE(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        logger.info("update");
        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null) {
            userId = (int) session.getAttribute("user");

        }
        try {
            int systolicPressureValue = Integer.parseInt(request.getParameter("systolicPressure"));
            int diastolicPressureValue = Integer.parseInt(request.getParameter("diastolicPressure"));
            DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate measureDate = LocalDate.parse(request.getParameter("measureDate"), f);

            User user = userDAO.getOne(userId);
            Pressure pressure = new Pressure();

            pressure.setUser(user);
            pressure.setMeasureDate(measureDate);
            pressure.setDiastolicPressureValue(diastolicPressureValue);
            pressure.setSystolicPressureValue(systolicPressureValue);

            // Register to database
            pressureDAO.update(pressure);
            logger.info("Update Successfully");
            List<Pressure> list = pressureDAO.getByUser(userId);
            request.setAttribute("pressures", list);
            request.setAttribute("message", "Atualização feita com sucesso");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("error", "Erro ao editar");
        } catch (Exception error) {
            error.printStackTrace();
            request.setAttribute("error", "Erro, por favor valide os dados");
        }
        request.getRequestDispatcher("pressure-list.jsp").forward(request, response);
    }

    protected void pressureDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("delete");
        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null) {
            userId = (int) session.getAttribute("user");

        }
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            pressureDAO.delete(id);
            List<Pressure> list = pressureDAO.getByUser(userId);
            request.setAttribute("pressures", list);
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("error", "Erro ao deletar");
        } catch (Exception error) {
            error.printStackTrace();
            request.setAttribute("error", "Erro, por favor valide os dados");
        }
        request.getRequestDispatcher("/pressure-list.jsp").forward(request, response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("List");
        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null) {
            userId = (int) session.getAttribute("user");

        }
        List<Pressure> list = pressureDAO.getByUser(userId);
        request.setAttribute("pressures", list);

        request.getRequestDispatcher("/pressure-list.jsp").forward(request, response);
    }

    protected void overTime(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("overTime");
        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null) {
            userId = (int) session.getAttribute("user");

        }
        List<Pressure> list = pressureDAO.getByUser(userId);
        String bodyMassJsonString = this.gson.toJson(list);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(bodyMassJsonString);
        out.flush();
    }

}
