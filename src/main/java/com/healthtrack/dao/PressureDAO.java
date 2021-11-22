package com.healthtrack.dao;

/**
* Pressure Data Access Object
* @author Gabriel Menezes
* @version 1.0
*/
import java.util.List;
import com.healthtrack.entity.Pressure;
import com.healthtrack.exception.DBException;

public interface PressureDAO {
    int register(Pressure pressure) throws DBException;

    void update(Pressure pressure) throws DBException;

    void delete(int id) throws DBException;

    List<Pressure> getAll();

    Pressure getOne(int id);

    List<Pressure> getByUser(int id);

}