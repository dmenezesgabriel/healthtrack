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
import com.healthtrack.entity.Height;
import com.healthtrack.exception.DBException;
import com.healthtrack.jdbc.ConnectionManager;
import com.healthtrack.util.Query;

public class HeightDAOImplPostgres implements HeightDAO {
    private Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());
    private Connection connection;

    @Override
    public int register(Height height) throws DBException {
        logger.info("Inserting Height");
        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/height_register.sql");
            stmt = (connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
            // Set values
            stmt.setInt(1, height.getUser().getId());
            stmt.setObject(2, height.getMeasureDate());
            stmt.setDouble(3, height.getMeasureValue());
            // Insert values
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new DBException("Creating height failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    logger.info("Inserted height id: " + insertedId);
                    return insertedId;
                } else {
                    throw new DBException("Creating height failed, no ID obtained.");
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error registering height");
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
    public void update(Height height) throws DBException {
        logger.info("Updating height");
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/height_update.sql");
            stmt = (connection.prepareStatement(sql));

            // Set values
            stmt.setObject(1, height.getMeasureDate());
            stmt.setDouble(2, height.getMeasureValue());
            stmt.setInt(3, height.getUser().getId());
            stmt.setInt(4, height.getId());
            stmt.executeUpdate();
            logger.info("Height updated id: " + height.getId());
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error updating height");
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
        logger.info("Deleting height id: " + id);
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/height_delete.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException error) {
            throw new DBException("Error deleting height");
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
    public List<Height> getAll() {
        logger.info("Getting all heights");

        List<Height> heightList = new ArrayList<Height>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/height_get_all.sql");
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_altura");
                LocalDate measureDate = result.getObject("dt_altura", LocalDate.class);
                double measureValue = result.getDouble("vl_altura");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");
                String password = result.getString("ds_senha");

                User user = new User(userId, name, birthDate, gender, email, password);

                Height height = new Height(id, user, measureDate, measureValue);
                heightList.add(height);
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
        return heightList;
    }

    @Override
    public List<Height> getByUser(int objectId) {
        logger.info("Getting all heights");

        List<Height> heightList = new ArrayList<Height>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/height_get_all_by_user.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_altura");
                LocalDate measureDate = result.getObject("dt_altura", LocalDate.class);
                double measureValue = result.getDouble("vl_altura");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");
                String password = result.getString("ds_senha");

                User user = new User(userId, name, birthDate, gender, email, password);
                Height height = new Height(id, user, measureDate, measureValue);
                heightList.add(height);
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
        return heightList;
    }

    @Override
    public Height getOne(int objectId) {
        logger.info("Getting height id: " + objectId);
        Height height = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/height_get_one.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            if (result.next()) {
                int id = result.getInt("cd_altura");
                LocalDate measureDate = result.getObject("dt_altura", LocalDate.class);
                double measureValue = result.getDouble("vl_altura");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");
                String password = result.getString("ds_senha");

                User user = new User(userId, name, birthDate, gender, email, password);
                height = new Height(id, user, measureDate, measureValue);
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
        return height;
    }

}
