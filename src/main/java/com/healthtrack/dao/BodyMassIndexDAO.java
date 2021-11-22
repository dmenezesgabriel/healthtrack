package com.healthtrack.dao;

/**
* BodyMassIndex Data Access Object
* @author Gabriel Menezes
* @version 1.0
*/
import java.util.List;
import com.healthtrack.entity.BodyMassIndex;
import com.healthtrack.exception.DBException;

public interface BodyMassIndexDAO {
    int register(BodyMassIndex bodyMassIndex) throws DBException;

    void update(BodyMassIndex bodyMassIndex) throws DBException;

    void delete(int id) throws DBException;

    List<BodyMassIndex> getAll();

    BodyMassIndex getOne(int id);

    List<BodyMassIndex> getByUser(int id);

}