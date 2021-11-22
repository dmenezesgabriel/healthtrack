package com.healthtrack.dao;

/**
* Height Data Access Object
* @author Gabriel Menezes
* @version 1.0
*/
import java.util.List;
import com.healthtrack.entity.Height;
import com.healthtrack.exception.DBException;

public interface HeightDAO {
    int register(Height height) throws DBException;

    void update(Height height) throws DBException;

    void delete(int id) throws DBException;

    List<Height> getAll();

    Height getOne(int id);

    List<Height> getByUser(int id);

}