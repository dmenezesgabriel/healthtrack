package com.healthtrack.dao;

/**
* User Data Access Object
* @author Gabriel Menezes
* @version 1.0
*/
import java.util.List;
import com.healthtrack.entity.User;

public interface UserDAO {
    boolean register(User user);

    List<User> getAll();

    User getOne(int id);

    boolean update(User user);

    boolean delete(int id);
}