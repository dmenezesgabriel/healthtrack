package com.healttrack.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.HeightDAO;
import com.healthtrack.entity.User;
import com.healthtrack.entity.Height;
import com.healthtrack.exception.DBException;
import com.healthtrack.factory.DAOFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HeightDAOTest {
    public static UserDAO userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
    public static HeightDAO heightDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getHeightDAO(userDAO));
    public static User userMock = null;
    public static Height heightMock = null;

    public static void mockUser() throws DBException {
        User user = new User();
        user.setName("Gabriel");
        user.setEmail("gabriel@example.com");
        user.setGender("Masculino");
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate birthDate = LocalDate.parse(input, f);
        user.setBirthDate(birthDate);
        user.setPassword("123");
        int userRegisteredId = userDAO.register(user);
        user = userDAO.getOne(userRegisteredId);
        userMock = user;
    }

    public static void mockHeight() throws DBException {
        Height height = new Height();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        height.setUser(userMock);
        height.setMeasureDate(measureDate);
        height.setMeasureValue(70.02);
        heightMock = height;
    }

    @BeforeClass
    public static void setUp() throws DBException {
        mockUser();
        mockHeight();
    }

    @Test
    public void shouldInsertObject() throws DBException {
        int heightRegisteredId = heightDAO.register(heightMock);
        assertTrue(heightRegisteredId > 0);
        Height height = heightDAO.getOne(heightRegisteredId);
        assertEquals(height.getMeasureValue(), heightMock.getMeasureValue(), 0.8);

    }

    @Test
    public void shouldGetOne() throws DBException {
        int heightRegisteredId = heightDAO.register(heightMock);
        Height height = heightDAO.getOne(heightRegisteredId);
        assertEquals(height.getMeasureValue(), heightMock.getMeasureValue(), 0.8);

    }

    @Test
    public void shouldGetAll() throws DBException {
        int heightRegisteredId = heightDAO.register(heightMock);
        List<Height> heightList = heightDAO.getAll();
        Height height = heightDAO.getOne(heightRegisteredId);
        assertEquals(heightList.get(heightList.size() - 1), height);
    }

    @Test
    public void shouldGetByUser() throws DBException {
        int heightRegisteredId = heightDAO.register(heightMock);
        List<Height> heightList = heightDAO.getByUser(userMock.getId());
        Height height = heightDAO.getOne(heightRegisteredId);
        assertEquals(heightList.get(heightList.size() - 1), height);
    }

    @Test
    public void shouldUpdate() throws DBException {
        int heightRegisteredId = heightDAO.register(heightMock);
        Height height = heightDAO.getOne(heightRegisteredId);
        double newValue = 71.5;
        height.setMeasureValue(newValue);
        heightDAO.update(height);
        assertEquals(heightDAO.getOne(heightRegisteredId).getMeasureValue(), newValue, 0.8);

    }

    @Test
    public void shouldDelete() throws DBException {
        int heightRegisteredId = heightDAO.register(heightMock);
        heightDAO.delete(heightRegisteredId);
        assertNull(heightDAO.getOne(heightRegisteredId));
    }
}
