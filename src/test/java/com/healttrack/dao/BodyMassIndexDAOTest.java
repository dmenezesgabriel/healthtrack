package com.healttrack.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.dao.BodyMassIndexDAO;
import com.healthtrack.dao.HeightDAO;
import com.healthtrack.dao.WeightDAO;

import com.healthtrack.entity.User;
import com.healthtrack.entity.BodyMassIndex;
import com.healthtrack.entity.Height;
import com.healthtrack.entity.Weight;

import com.healthtrack.exception.DBException;
import com.healthtrack.factory.DAOFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BodyMassIndexDAOTest {

    public static UserDAO userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());
    public static HeightDAO heightDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getHeightDAO());
    public static WeightDAO weightDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getWeightDAO());
    public static BodyMassIndexDAO bodyMassIndexDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES)
            .getBodyMassIndexDAO());

    public static User userMock = null;
    public static Height heightMock = null;
    public static Weight weightMock = null;
    public static BodyMassIndex bodyMassIndexMock = null;

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
        height.setMeasureValue(1.74);
        int heightRegisteredId = heightDAO.register(height);
        height = heightDAO.getOne(heightRegisteredId);
        heightMock = height;
    }

    public static void mockWeight() throws DBException {
        Weight weight = new Weight();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        weight.setUser(userMock);
        weight.setMeasureDate(measureDate);
        weight.setMeasureValue(70.02);
        int weightRegisteredId = weightDAO.register(weight);
        weight = weightDAO.getOne(weightRegisteredId);
        weightMock = weight;
    }

    public static void mockBodyMassIndex() throws DBException {
        BodyMassIndex bmi = new BodyMassIndex();
        bmi.setHeight(heightMock);
        bmi.setWeight(weightMock);
        bmi.setUser(userMock);
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        bmi.setMeasureDate(measureDate);
        bmi.setMeasureValue(bmi.calculateIndex());
        bodyMassIndexMock = bmi;

    }

    @BeforeClass
    public static void setUp() throws DBException {
        mockUser();
        mockHeight();
        mockWeight();
        mockBodyMassIndex();
    }

    @Test
    public void shouldInsertObject() throws DBException {
        int bodyMassIndexRegisteredId = bodyMassIndexDAO.register(bodyMassIndexMock);
        assertTrue(bodyMassIndexRegisteredId > 0);
        BodyMassIndex bodyMassIndex = bodyMassIndexDAO.getOne(bodyMassIndexRegisteredId);
        assertEquals(bodyMassIndex.getMeasureValue(), bodyMassIndexMock.getMeasureValue(), 0.8);

    }

    @Test
    public void shouldGetOne() throws DBException {
        int bodyMassIndexRegisteredId = bodyMassIndexDAO.register(bodyMassIndexMock);
        BodyMassIndex bodyMassIndex = bodyMassIndexDAO.getOne(bodyMassIndexRegisteredId);
        assertEquals(bodyMassIndex.getMeasureValue(), bodyMassIndexMock.getMeasureValue(), 0.8);

    }

    @Test
    public void shouldGetAll() throws DBException {
        int bodyMassIndexRegisteredId = bodyMassIndexDAO.register(bodyMassIndexMock);
        List<BodyMassIndex> bodyMassIndexList = bodyMassIndexDAO.getAll();
        BodyMassIndex bodyMassIndex = bodyMassIndexDAO.getOne(bodyMassIndexRegisteredId);
        assertEquals(bodyMassIndexList.get(bodyMassIndexList.size() - 1), bodyMassIndex);
    }

    @Test
    public void shouldGetByUser() throws DBException {
        int bodyMassIndexRegisteredId = bodyMassIndexDAO.register(bodyMassIndexMock);
        List<BodyMassIndex> bodyMassIndexList = bodyMassIndexDAO.getByUser(userMock.getId());
        BodyMassIndex bodyMassIndex = bodyMassIndexDAO.getOne(bodyMassIndexRegisteredId);
        assertEquals(bodyMassIndexList.get(bodyMassIndexList.size() - 1).getMeasureValue(),
                bodyMassIndex.getMeasureValue(), 0.8);
    }

    @Test
    public void shouldUpdate() throws DBException {
        int bodyMassIndexRegisteredId = bodyMassIndexDAO.register(bodyMassIndexMock);
        BodyMassIndex bodyMassIndex = bodyMassIndexDAO.getOne(bodyMassIndexRegisteredId);
        double newValue = 22.75;
        bodyMassIndex.setMeasureValue(newValue);
        bodyMassIndexDAO.update(bodyMassIndex);
        assertEquals(bodyMassIndexDAO.getOne(bodyMassIndexRegisteredId).getMeasureValue(), newValue, 0.8);

    }

    @Test
    public void shouldDelete() throws DBException {
        int bodyMassIndexRegisteredId = bodyMassIndexDAO.register(bodyMassIndexMock);
        bodyMassIndexDAO.delete(bodyMassIndexRegisteredId);
        assertNull(bodyMassIndexDAO.getOne(bodyMassIndexRegisteredId));
    }
}
