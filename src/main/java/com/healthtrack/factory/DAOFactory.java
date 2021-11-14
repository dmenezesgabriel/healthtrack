package com.healthtrack.factory;

import com.healthtrack.dao.UserDAO;

public abstract class DAOFactory {

    public static final int ORACLE = 1;
    public static final int POSTGRES = 2;
    private static DAOFactory oracleDAOFactory;
    private static DAOFactory postgresDAOFactory;

    public static DAOFactory getDAOFactory(int database) {
        switch (database) {
        case ORACLE:
            if (oracleDAOFactory == null) {
                oracleDAOFactory = new DAOFactoryOracle();
                return oracleDAOFactory;
            } else {
                return oracleDAOFactory;
            }
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
