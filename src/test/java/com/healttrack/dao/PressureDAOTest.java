package com.healttrack.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.PressureDAO;
import com.healthtrack.entity.User;
import com.healthtrack.entity.Pressure;
import com.healthtrack.exception.DBException;
import com.healthtrack.factory.DAOFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PressureDAOTest {
    public static UserDAO userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
    public static PressureDAO pressureDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getPressureDAO());
    public static User userMock = null;
    public static Pressure pressureMock = null;

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

    public static void mockPressure() throws DBException {
        Pressure pressure = new Pressure();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        pressure.setUser(userMock);
        pressure.setMeasureDate(measureDate);
        pressure.setDiastolicPressureValue(90);
        pressure.setSystolicPressureValue(60);
        pressureMock = pressure;
    }

    @BeforeClass
    public static void setUp() throws DBException {
        mockUser();
        mockPressure();
    }

    @Test
    public void shouldInsertObject() throws DBException {
        int pressureRegisteredId = pressureDAO.register(pressureMock);
        assertTrue(pressureRegisteredId > 0);
        Pressure pressure = pressureDAO.getOne(pressureRegisteredId);
        assertTrue(pressure.getSystolicPressureValue() == pressureMock.getSystolicPressureValue());

    }

    @Test
    public void shouldGetOne() throws DBException {
        int pressureRegisteredId = pressureDAO.register(pressureMock);
        Pressure pressure = pressureDAO.getOne(pressureRegisteredId);
        assertTrue(pressure.getSystolicPressureValue() == pressureMock.getSystolicPressureValue());

    }

    @Test
    public void shouldGetAll() throws DBException {
        int pressureRegisteredId = pressureDAO.register(pressureMock);
        List<Pressure> pressureList = pressureDAO.getAll();
        Pressure pressure = pressureDAO.getOne(pressureRegisteredId);
        assertEquals(pressureList.get(pressureList.size() - 1), pressure);
    }

    @Test
    public void shouldGetByUser() throws DBException {
        int pressureRegisteredId = pressureDAO.register(pressureMock);
        List<Pressure> pressureList = pressureDAO.getByUser(userMock.getId());
        Pressure pressure = pressureDAO.getOne(pressureRegisteredId);
        assertEquals(pressureList.get(pressureList.size() - 1), pressure);
    }

    @Test
    public void shouldUpdate() throws DBException {
        int pressureRegisteredId = pressureDAO.register(pressureMock);
        Pressure pressure = pressureDAO.getOne(pressureRegisteredId);
        int newValue = 100;
        pressure.setDiastolicPressureValue(newValue);
        pressureDAO.update(pressure);
        assertTrue(pressureDAO.getOne(pressureRegisteredId).getDiastolicPressureValue() == newValue);

    }

    @Test
    public void shouldDelete() throws DBException {
        int pressureRegisteredId = pressureDAO.register(pressureMock);
        pressureDAO.delete(pressureRegisteredId);
        assertNull(pressureDAO.getOne(pressureRegisteredId));
    }
}
