package com.healthtrack.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;

import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;

import com.healthtrack.entity.User;
import com.healthtrack.entity.Pressure;
import com.healthtrack.exception.DBException;
import com.healthtrack.jdbc.ConnectionManager;
import com.healthtrack.util.Query;

public class PressureDAOImplPostgres implements PressureDAO {
    private Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());
    private Connection connection;

    @Override
    public int register(Pressure pressure) throws DBException {
        logger.info("Inserting Pressure");
        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/pressure_register.sql");
            stmt = (connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
            // Set values
            stmt.setInt(1, pressure.getUser().getId());
            stmt.setObject(2, pressure.getMeasureDate());
            stmt.setInt(3, pressure.getSystolicPressureValue());
            stmt.setInt(4, pressure.getDiastolicPressureValue());

            // Insert values
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new DBException("Creating pressure failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    logger.info("Inserted pressure id: " + insertedId);
                    return insertedId;
                } else {
                    throw new DBException("Creating pressure failed, no ID obtained.");
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error registering pressure");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }

    @Override
    public void update(Pressure pressure) throws DBException {
        logger.info("Updating pressure");
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/pressure_update.sql");
            stmt = (connection.prepareStatement(sql));

            // Set values
            stmt.setInt(1, pressure.getUser().getId());
            stmt.setObject(2, pressure.getMeasureDate());
            stmt.setInt(3, pressure.getSystolicPressureValue());
            stmt.setInt(4, pressure.getDiastolicPressureValue());
            stmt.setInt(5, pressure.getId());

            stmt.executeUpdate();
            logger.info("Pressure updated id: " + pressure.getId());
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error updating pressure");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) throws DBException {
        logger.info("Deleting pressure id: " + id);
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/pressure_delete.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException error) {
            throw new DBException("Error deleting pressure");
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }

    @Override
    public List<Pressure> getAll() {
        logger.info("Getting all pressures");

        List<Pressure> pressureList = new ArrayList<Pressure>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/pressure_get_all.sql");
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_pressao");
                LocalDate measureDate = result.getObject("dt_pressao", LocalDate.class);
                int systolicPressureValue = result.getInt("vl_pressao_sistolica");
                int diastolicPressureValue = result.getInt("vl_pressao_diastolica");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");

                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setBirthDate(birthDate);
                user.setGender(gender);
                user.setEmail(email);

                Pressure pressure = new Pressure(id, user, measureDate, systolicPressureValue, diastolicPressureValue);
                pressureList.add(pressure);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                stmt.close();
                result.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return pressureList;
    }

    @Override
    public List<Pressure> getByUser(int objectId) {
        logger.info("Getting all pressures");

        List<Pressure> pressureList = new ArrayList<Pressure>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/pressure_get_all_by_user.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_pressao");
                LocalDate measureDate = result.getObject("dt_pressao", LocalDate.class);
                int systolicPressureValue = result.getInt("vl_pressao_sistolica");
                int diastolicPressureValue = result.getInt("vl_pressao_diastolica");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");

                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setBirthDate(birthDate);
                user.setGender(gender);
                user.setEmail(email);

                Pressure pressure = new Pressure(id, user, measureDate, systolicPressureValue, diastolicPressureValue);
                pressureList.add(pressure);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                stmt.close();
                result.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return pressureList;
    }

    @Override
    public Pressure getOne(int objectId) {
        logger.info("Getting pressure id: " + objectId);
        Pressure pressure = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/pressure_get_one.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            if (result.next()) {
                int id = result.getInt("cd_pressao");
                LocalDate measureDate = result.getObject("dt_pressao", LocalDate.class);
                int systolicPressureValue = result.getInt("vl_pressao_sistolica");
                int diastolicPressureValue = result.getInt("vl_pressao_diastolica");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");

                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setBirthDate(birthDate);
                user.setGender(gender);
                user.setEmail(email);

                pressure = new Pressure(id, user, measureDate, systolicPressureValue, diastolicPressureValue);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                stmt.close();
                result.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return pressure;
    }

}
