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

    void update(User user) throws DBException;

    void delete(int id) throws DBException;

    List<User> getAll();

    User getOne(int id);

    int validateUser(User user);

}