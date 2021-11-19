package com.healthtrack.dao;

/**
* User Data Access Object
* @author Gabriel Menezes
* @version 1.0
*/
import java.util.List;
import com.healthtrack.entity.User;
import com.healthtrack.exception.DBException;

public interface UserDAO {
    int register(User user) throws DBException;

    boolean update(User user) throws DBException;

    boolean delete(int id) throws DBException;

    List<User> getAll();

    User getOne(int id);

    // boolean validate(String email, String password);

}