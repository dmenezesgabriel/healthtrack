package com.healthtrack.factory;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.UserDAOImplOracle;

public class DAOFactoryOracle extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImplOracle();
    }

}