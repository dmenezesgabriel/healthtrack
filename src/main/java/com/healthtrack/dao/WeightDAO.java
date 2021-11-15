package com.healthtrack.dao;

/**
* Weight Data Access Object
* @author Gabriel Menezes
* @version 1.0
*/
import java.util.List;
import com.healthtrack.entity.Weight;

public interface WeightDAO {
    int register(Weight weight);

    List<Weight> getAll();

    Weight getOne(int id);

    boolean update(Weight weight);

    boolean delete(int id);
}