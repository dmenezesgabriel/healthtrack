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
import com.healthtrack.entity.BodyMassIndex;
import com.healthtrack.entity.Height;
import com.healthtrack.exception.DBException;
import com.healthtrack.jdbc.ConnectionManager;
import com.healthtrack.util.Query;

public class BodyMassIndexDAOImplPostgres implements BodyMassIndexDAO {
    private Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());
    private Connection connection;

    @Override
    public int register(BodyMassIndex bodyMassIndex) throws DBException {
        logger.info("Inserting BodyMassIndex");
        PreparedStatement stmt = null;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/bmi_register.sql");
            stmt = (connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
            // Set values
            stmt.setInt(1, bodyMassIndex.getUser().getId());
            stmt.setInt(2, bodyMassIndex.getWeight().getId());
            stmt.setInt(3, bodyMassIndex.getHeight().getId());
            stmt.setObject(4, bodyMassIndex.getMeasureDate());
            stmt.setDouble(5, bodyMassIndex.getMeasureValue());
            // Insert values
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new DBException("Creating BodyMassIndex failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    logger.info("Inserted BodyMassIndex id: " + insertedId);
                    return insertedId;
                } else {
                    throw new DBException("Creating BodyMassIndex failed, no ID obtained.");
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error registering BodyMassIndex");
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
    public void update(BodyMassIndex bodyMassIndex) throws DBException {
        logger.info("Updating BodyMassIndex");
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/bmi_update.sql");
            stmt = (connection.prepareStatement(sql));

            // Set values
            stmt.setInt(1, bodyMassIndex.getUser().getId());
            stmt.setInt(2, bodyMassIndex.getWeight().getId());
            stmt.setInt(3, bodyMassIndex.getHeight().getId());
            stmt.setObject(4, bodyMassIndex.getMeasureDate());
            stmt.setDouble(5, bodyMassIndex.getMeasureValue());
            stmt.setInt(6, bodyMassIndex.getId());
            stmt.executeUpdate();
            logger.info("BodyMassIndex updated id: " + bodyMassIndex.getId());
        } catch (SQLException error) {
            error.printStackTrace();
            throw new DBException("Error updating BodyMassIndex");
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
        logger.info("Deleting BodyMassIndex id: " + id);
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/bmi_delete.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException error) {
            throw new DBException("Error deleting BodyMassIndex");
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
    public List<BodyMassIndex> getAll() {
        logger.info("Getting all BodyMassIndexs");

        List<BodyMassIndex> BodyMassIndexList = new ArrayList<BodyMassIndex>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/bmi_get_all.sql");
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_imc");
                LocalDate measureDate = result.getObject("dt_imc", LocalDate.class);
                double measureValue = result.getDouble("vl_imc");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");

                int heightId = result.getInt("cd_altura");
                LocalDate heightDate = result.getObject("dt_altura", LocalDate.class);
                double heightValue = result.getDouble("vl_altura");

                int weightId = result.getInt("cd_peso");
                LocalDate weightDate = result.getObject("dt_peso", LocalDate.class);
                double weightValue = result.getDouble("vl_peso");

                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setBirthDate(birthDate);
                user.setGender(gender);
                user.setEmail(email);
                Height height = new Height(heightId, user, heightDate, heightValue);
                Weight weight = new Weight(weightId, user, weightDate, weightValue);

                BodyMassIndex BodyMassIndex = new BodyMassIndex();
                BodyMassIndex.setId(id);
                BodyMassIndex.setHeight(height);
                BodyMassIndex.setWeight(weight);
                BodyMassIndex.setUser(user);
                BodyMassIndex.setMeasureDate(measureDate);
                BodyMassIndex.setMeasureValue(measureValue);
                BodyMassIndexList.add(BodyMassIndex);
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
        return BodyMassIndexList;
    }

    @Override
    public List<BodyMassIndex> getByUser(int objectId) {
        logger.info("Getting all BodyMassIndexs");

        List<BodyMassIndex> BodyMassIndexList = new ArrayList<BodyMassIndex>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/bmi_get_all_by_user.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("cd_imc");
                LocalDate measureDate = result.getObject("dt_imc", LocalDate.class);
                double measureValue = result.getDouble("vl_imc");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");

                int heightId = result.getInt("cd_altura");
                LocalDate heightDate = result.getObject("dt_altura", LocalDate.class);
                double heightValue = result.getDouble("vl_altura");

                int weightId = result.getInt("cd_peso");
                LocalDate weightDate = result.getObject("dt_peso", LocalDate.class);
                double weightValue = result.getDouble("vl_peso");

                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setBirthDate(birthDate);
                user.setGender(gender);
                user.setEmail(email);
                Height height = new Height(heightId, user, heightDate, heightValue);
                Weight weight = new Weight(weightId, user, weightDate, weightValue);
                BodyMassIndex BodyMassIndex = new BodyMassIndex();
                BodyMassIndex.setId(id);
                BodyMassIndex.setHeight(height);
                BodyMassIndex.setWeight(weight);
                BodyMassIndex.setUser(user);
                BodyMassIndex.setMeasureDate(measureDate);
                BodyMassIndex.setMeasureValue(measureValue);
                BodyMassIndexList.add(BodyMassIndex);
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
        return BodyMassIndexList;
    }

    @Override
    public BodyMassIndex getOne(int objectId) {
        logger.info("Getting BodyMassIndex id: " + objectId);
        BodyMassIndex BodyMassIndex = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String sql = Query.fileToString("/bmi_get_one.sql");
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objectId);
            result = stmt.executeQuery();
            if (result.next()) {
                int id = result.getInt("cd_imc");
                LocalDate measureDate = result.getObject("dt_imc", LocalDate.class);
                double measureValue = result.getDouble("vl_imc");

                int userId = result.getInt("cd_usuario");
                String name = result.getString("nm_usuario");
                LocalDate birthDate = result.getObject("dt_nascimento", LocalDate.class);
                String gender = result.getString("ds_genero");
                String email = result.getString("ds_email");

                int heightId = result.getInt("cd_altura");
                LocalDate heightDate = result.getObject("dt_altura", LocalDate.class);
                double heightValue = result.getDouble("vl_altura");

                int weightId = result.getInt("cd_peso");
                LocalDate weightDate = result.getObject("dt_peso", LocalDate.class);
                double weightValue = result.getDouble("vl_peso");

                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setBirthDate(birthDate);
                user.setGender(gender);
                user.setEmail(email);
                Height height = new Height(heightId, user, heightDate, heightValue);
                Weight weight = new Weight(weightId, user, weightDate, weightValue);
                BodyMassIndex = new BodyMassIndex();
                BodyMassIndex.setId(id);
                BodyMassIndex.setHeight(height);
                BodyMassIndex.setWeight(weight);
                BodyMassIndex.setUser(user);
                BodyMassIndex.setMeasureDate(measureDate);
                BodyMassIndex.setMeasureValue(measureValue);
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
        return BodyMassIndex;
    }

}
