package com.healthtrack.factory;

import com.healthtrack.dao.UserDAO;

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

}
