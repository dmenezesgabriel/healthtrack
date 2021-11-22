package com.healthtrack.factory;

import com.healthtrack.dao.BodyMassIndexDAO;
import com.healthtrack.dao.HeightDAO;
import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.WeightDAO;

public abstract class DAOFactory {
    public static final int POSTGRES = 1;
    private static DAOFactory postgresDAOFactory;

    public static DAOFactory getDAOFactory(int database) {
        switch (database) {
        case POSTGRES:
            if (postgresDAOFactory == null) {
                postgresDAOFactory = new DAOFactoryPostgres();
                return postgresDAOFactory;
            } else {
                return postgresDAOFactory;
            }
        default:
            return null;
        }
    }

    public abstract UserDAO getUserDAO();

    public abstract WeightDAO getWeightDAO(UserDAO userDao);

    public abstract HeightDAO getHeightDAO(UserDAO userDao);

    public abstract BodyMassIndexDAO getBodyMassIndexDAO(UserDAO userDAO, HeightDAO heightDAO, WeightDAO weightDAO);

}
