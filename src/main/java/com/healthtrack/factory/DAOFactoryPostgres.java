package com.healthtrack.factory;

import com.healthtrack.dao.HeightDAO;
import com.healthtrack.dao.HeightDAOImplPostgres;
import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.UserDAOImplPostgres;
import com.healthtrack.dao.WeightDAO;
import com.healthtrack.dao.WeightDAOImplPostgres;

public class DAOFactoryPostgres extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImplPostgres();
    }

    @Override
    public WeightDAO getWeightDAO(UserDAO userDao) {
        return new WeightDAOImplPostgres(userDao);
    }

    @Override
    public HeightDAO getHeightDAO(UserDAO userDao) {
        return new HeightDAOImplPostgres(userDao);
    }
}