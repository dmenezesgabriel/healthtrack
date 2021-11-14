package com.healthtrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import com.healthtrack.entity.User;
import com.healthtrack.jdbc.ConnectionManager;
import com.healthtrack.util.Query;

public class UserDAOImplPostgres implements UserDAO {
    private Connection connection;

    @Override
    public int register(User user) {
        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("user_register.sql");
            stmt = (connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
            // Set values
            stmt.setString(1, user.getName());
            java.sql.Date date = (new java.sql.Date(user.getBirthDate().getTimeInMillis()));
            stmt.setDate(2, date);
            stmt.setString(3, user.getGender());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            // Insert values
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    return insertedId;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
            return 0;
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
    public boolean update(User user) {
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("user_update.sql");
            stmt = (connection.prepareStatement(sql));

            // Set values
            stmt.setString(1, user.getName());
            java.sql.Date date = (new java.sql.Date(user.getBirthDate().getTimeInMillis()));
            stmt.setDate(2, date);
            stmt.setString(3, user.getGender());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException error) {
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
    public List<User> getAll() {
        List<User> userList = new ArrayList<User>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("user_get_all.sql");
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                java.sql.Date date = result.getDate("dt_nascimento");
                Calendar birthDate = Calendar.getInstance();
                birthDate.setTimeInMillis(date.getTime());
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
    public User getOne(int userId) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("user_get_one.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            result = stmt.executeQuery();
            if (result.next()) {
                int id = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                java.sql.Date date = result.getDate("dt_nascimento");
                Calendar birthDate = Calendar.getInstance();
                birthDate.setTimeInMillis(date.getTime());
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

    @Override
    public boolean delete(int id) {
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("user_delete.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        }
    }
}
