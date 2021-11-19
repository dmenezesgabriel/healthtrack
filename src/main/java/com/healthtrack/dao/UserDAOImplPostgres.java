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
import com.healthtrack.exception.DBException;
import com.healthtrack.jdbc.ConnectionManager;
import com.healthtrack.util.Query;

public class UserDAOImplPostgres implements UserDAO {
    Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());
    private Connection connection;

    @Override
    public int register(User user) throws DBException {
        logger.info("Inserting User");
        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/user_register.sql");
            stmt = (connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
            // Set values
            stmt.setString(1, user.getName());
            stmt.setObject(2, user.getBirthDate());
            stmt.setString(3, user.getGender());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            // Insert values
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new DBException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    logger.info("Inserted user id: " + insertedId);
                    return insertedId;
                } else {
                    throw new DBException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error registering user");
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
    public boolean update(User user) throws DBException {
        logger.info("Updating user");
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/user_update.sql");
            stmt = (connection.prepareStatement(sql));

            // Set values
            stmt.setString(1, user.getName());
            stmt.setObject(2, user.getBirthDate());
            stmt.setString(3, user.getGender());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
            logger.info("User updated id: " + user.getId());
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error updating user");
        } catch (Exception error) {
            error.printStackTrace();
            return false;
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
    public boolean delete(int id) throws DBException {
        logger.info("Deleting user id: " + id);
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/user_delete.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error deleting user");
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        logger.info("Getting all users");
        List<User> userList = new ArrayList<User>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/user_get_all.sql");
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");
                String password = result.getString("ds_senha");

                User user = new User(id, name, birthDate, gender, email, password);
                userList.add(user);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public User getOne(int objectId) {
        logger.info("Getting user id: " + objectId);
        User user = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/user_get_one.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            if (result.next()) {
                int id = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");
                String password = result.getString("ds_senha");

                user = new User(id, name, birthDate, gender, email, password);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return user;
    }

}
