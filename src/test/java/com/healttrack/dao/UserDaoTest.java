package com.healttrack.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.entity.User;
import com.healthtrack.exception.DBException;
import com.healthtrack.factory.DAOFactory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserDaoTest {
    UserDAO userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());

    public User mockUser() {
        User user = new User();
        user.setName("Gabriel");
        user.setEmail("gabriel@example.com");
        user.setGender("Masculino");
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate birthDate = LocalDate.parse(input, f);
        user.setBirthDate(birthDate);
        user.setPassword("123");
        return user;
    }

    @Test
    public void shouldInsertObject() throws DBException {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        assertTrue(userRegisteredId > 0);
        User user = userDAO.getOne(userRegisteredId);
        assertEquals(user.getName(), userMock.getName());

    }

    @Test
    public void shouldGetOne() throws DBException {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        User user = userDAO.getOne(userRegisteredId);
        assertEquals(user.getName(), userMock.getName());
    }

    @Test
    public void shouldGetAll() throws DBException {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        List<User> userList = userDAO.getAll();
        User user = userDAO.getOne(userRegisteredId);
        assertEquals(userList.get(userList.size() - 1), user);
    }

    @Test
    public void shouldUpdate() throws DBException {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        User user = userDAO.getOne(userRegisteredId);
        String newName = "UpdateTest";
        user.setName(newName);
        userDAO.update(user);
        assertEquals(userDAO.getOne(userRegisteredId).getName(), newName);
    }

    @Test
    public void shouldDelete() throws DBException {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        userDAO.delete(userRegisteredId);
        assertNull(userDAO.getOne(userRegisteredId));
    }
}
