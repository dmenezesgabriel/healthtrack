package com.healttrack.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.WeightDAO;
import com.healthtrack.entity.User;
import com.healthtrack.entity.Weight;
import com.healthtrack.exception.DBException;
import com.healthtrack.factory.DAOFactory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class WeightDAOTest {
    UserDAO userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
    WeightDAO weightDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getWeightDAO(userDAO));

    public User mockUser() throws DBException {
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
        return user;
    }

    public Weight mockWeight() throws DBException {
        User user = mockUser();
        Weight weight = new Weight();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        weight.setUser(user);
        weight.setMeasureDate(measureDate);
        weight.setMeasureValue(70.02);
        return weight;
    }

    @Test
    public void shouldInsertObject() throws DBException {
        Weight weightMock = mockWeight();
        int weightRegisteredId = weightDAO.register(weightMock);
        assertTrue(weightRegisteredId > 0);
        Weight weight = weightDAO.getOne(weightRegisteredId);
        assertEquals(weight.getMeasureValue(), weightMock.getMeasureValue(), 0.8);

    }

    @Test
    public void shouldGetOne() throws DBException {
        Weight weightMock = mockWeight();
        int weightRegisteredId = weightDAO.register(weightMock);
        Weight weight = weightDAO.getOne(weightRegisteredId);
        assertEquals(weight.getMeasureValue(), weightMock.getMeasureValue(), 0.8);

    }

    @Test
    public void shouldGetAll() throws DBException {
        Weight weightMock = mockWeight();
        int weightRegisteredId = weightDAO.register(weightMock);
        List<Weight> weightList = weightDAO.getAll();
        Weight weight = weightDAO.getOne(weightRegisteredId);
        assertEquals(weightList.get(weightList.size() - 1), weight);
    }

    @Test
    public void shouldUpdate() throws DBException {
        Weight weightMock = mockWeight();
        int weightRegisteredId = weightDAO.register(weightMock);
        Weight weight = weightDAO.getOne(weightRegisteredId);
        double newValue = 71.5;
        weight.setMeasureValue(newValue);
        weightDAO.update(weight);
        assertEquals(weightDAO.getOne(weightRegisteredId).getMeasureValue(), newValue, 0.8);

    }

    @Test
    public void shouldDelete() throws DBException {
        Weight weightMock = mockWeight();
        int weightRegisteredId = weightDAO.register(weightMock);
        weightDAO.delete(weightRegisteredId);
        assertNull(weightDAO.getOne(weightRegisteredId));
    }
}
