package com.healthtrack.factory;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.UserDAOImplPostgres;

public class DAOFactoryPostgres extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImplPostgres();
    }

}