package com.healttrack.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.entity.User;
import com.healthtrack.factory.DAOFactory;

import org.junit.Test;
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
    public void shouldInsertObject() {
        User user = mockUser();
        int userRegisteredId = userDAO.register(user);
        assertTrue(userRegisteredId > 0);
    }

    @Test
    public void shouldGetOne() {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        User user = userDAO.getOne(userRegisteredId);
        assertTrue(user.getName().equals(userMock.getName()));
    }

    @Test
    public void shouldGetAll() {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        List<User> userList = userDAO.getAll();
        User user = userDAO.getOne(userRegisteredId);
        assertTrue(userList.get(userList.size() - 1).equals(user));
    }

    @Test
    public void shouldUpdate() {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        User user = userDAO.getOne(userRegisteredId);
        String newName = "UpdateTest";
        user.setName(newName);
        boolean userUpdated = userDAO.update(user);
        assertTrue(userUpdated);
        assertTrue(userDAO.getOne(userRegisteredId).getName().equals(newName));
    }

    @Test
    public void shouldDelete() {
        User userMock = mockUser();
        int userRegisteredId = userDAO.register(userMock);
        boolean userDeleted = userDAO.delete(userRegisteredId);
        assertTrue(userDeleted);
    }
}
