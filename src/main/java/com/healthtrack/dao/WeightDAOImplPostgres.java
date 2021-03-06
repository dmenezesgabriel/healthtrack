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
import com.healthtrack.entity.Weight;
import com.healthtrack.exception.DBException;
import com.healthtrack.jdbc.ConnectionManager;
import com.healthtrack.util.Query;

public class WeightDAOImplPostgres implements WeightDAO {
    private Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());
    private Connection connection;

    @Override
    public int register(Weight weight) throws DBException {
        logger.info("Inserting Weight");
        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_register.sql");
            stmt = (connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
            // Set values
            stmt.setInt(1, weight.getUser().getId());
            stmt.setObject(2, weight.getMeasureDate());
            stmt.setDouble(3, weight.getMeasureValue());
            // Insert values
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new DBException("Creating weight failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    logger.info("Inserted weight id: " + insertedId);
                    return insertedId;
                } else {
                    throw new DBException("Creating weight failed, no ID obtained.");
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error registering weight");
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
    public void update(Weight weight) throws DBException {
        logger.info("Updating weight");
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_update.sql");
            stmt = (connection.prepareStatement(sql));

            // Set values
            stmt.setObject(1, weight.getMeasureDate());
            stmt.setDouble(2, weight.getMeasureValue());
            stmt.setInt(3, weight.getUser().getId());
            stmt.setInt(4, weight.getId());
            stmt.executeUpdate();
            logger.info("Weight updated id: " + weight.getId());
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error updating weight");
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
        logger.info("Deleting weight id: " + id);
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_delete.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException error) {
            throw new DBException("Error deleting weight");
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
    public List<Weight> getAll() {
        logger.info("Getting all weights");

        List<Weight> weightList = new ArrayList<Weight>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_get_all.sql");
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_peso");
                LocalDate measureDate = result.getObject("dt_peso", LocalDate.class);
                double measureValue = result.getDouble("vl_peso");

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
                Weight weight = new Weight(id, user, measureDate, measureValue);
                weightList.add(weight);
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
        return weightList;
    }

    @Override
    public List<Weight> getByUser(int objectId) {
        logger.info("Getting all weights");

        List<Weight> weightList = new ArrayList<Weight>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_get_all_by_user.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_peso");
                LocalDate measureDate = result.getObject("dt_peso", LocalDate.class);
                double measureValue = result.getDouble("vl_peso");

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
                Weight weight = new Weight(id, user, measureDate, measureValue);
                weightList.add(weight);
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
        return weightList;
    }

    @Override
    public Weight getOne(int objectId) {
        logger.info("Getting weight id: " + objectId);
        Weight weight = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_get_one.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            if (result.next()) {
                int id = result.getInt("cd_peso");
                LocalDate measureDate = result.getObject("dt_peso", LocalDate.class);
                double measureValue = result.getDouble("vl_peso");

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
                weight = new Weight(id, user, measureDate, measureValue);
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
        return weight;
    }

}
