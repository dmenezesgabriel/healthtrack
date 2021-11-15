package com.healttrack.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.healthtrack.dao.UserDAO;
import com.healthtrack.entity.User;
import com.healthtrack.factory.DAOFactory;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class UserDaoTest {
    UserDAO userDAO = (DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getUserDAO());

    @Test
    public void shouldAnswerWithTrue() {
        User user = new User();
        user.setName("Gabriel");
        user.setEmail("gabriel@example.com");
        user.setGender("Masculino");
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate birthDate = LocalDate.parse(input, f);
        user.setBirthDate(birthDate);
        user.setPassword("123");
        assertTrue(user.getName() == "Gabriel");

        int userRegistered = userDAO.register(user);

    }
}
