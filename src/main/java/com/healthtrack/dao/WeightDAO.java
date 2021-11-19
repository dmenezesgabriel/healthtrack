package com.healthtrack.dao;

/**
* Weight Data Access Object
* @author Gabriel Menezes
* @version 1.0
*/
import java.util.List;
import com.healthtrack.entity.Weight;
import com.healthtrack.exception.DBException;

public interface WeightDAO {
    int register(Weight weight) throws DBException;

    boolean update(Weight weight) throws DBException;

    boolean delete(int id) throws DBException;

    List<Weight> getAll();

    Weight getOne(int id);

}