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

import com.healthtrack.entity.Weight;
import com.healthtrack.jdbc.ConnectionManager;
import com.healthtrack.util.Query;

public class WeightDAOImplPostgres implements WeightDAO {
    Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());
    private Connection connection;

    @Override
    public int register(Weight weight) {
        logger.info("Inserting Weight");
        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_register.sql");
            stmt = (connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
            // Set values
            stmt.setString(1, weight.getName());
            stmt.setObject(2, weight.getBirthDate());
            stmt.setString(3, weight.getGender());
            stmt.setString(4, weight.getEmail());
            stmt.setString(5, weight.getPassword());
            // Insert values
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating weight failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    logger.info("Inserted weight id: " + insertedId);
                    return insertedId;
                } else {
                    throw new SQLException("Creating weight failed, no ID obtained.");
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
            return 0;
        } catch (Exception error) {
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
    public boolean update(Weight weight) {
        logger.info("Updating weight");
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_update.sql");
            stmt = (connection.prepareStatement(sql));

            // Set values
            stmt.setString(1, weight.getName());
            stmt.setObject(2, weight.getBirthDate());
            stmt.setString(3, weight.getGender());
            stmt.setString(4, weight.getEmail());
            stmt.setString(5, weight.getPassword());
            stmt.setInt(6, weight.getId());
            stmt.executeUpdate();
            logger.info("Weight updated id: " + weight.getId());
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
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
                int id = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");
                String password = result.getString("ds_senha");

                Weight weight = new Weight(id, name, birthDate, gender, email, password);
                weightList.add(weight);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return weightList;
    }

    @Override
    public Weight getOne(int id) {
        logger.info("Getting weight id: " + id);
        Weight weight = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_get_one.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            result = stmt.executeQuery();
            if (result.next()) {
                int objectId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");
                String password = result.getString("ds_senha");

                weight = new Weight(objectId, name, birthDate, gender, email, password);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return weight;
    }

    @Override
    public boolean delete(int id) {
        logger.info("Deleting weight id: " + id);
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/weight_delete.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }
}
