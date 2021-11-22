package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.healthtrack.entity.User;
import com.healthtrack.entity.Weight;

import org.junit.BeforeClass;
import org.junit.Test;

public class WeightTest {
    public static User userMock = null;

    @BeforeClass
    public static void mockUser() {
        User user = new User();
        user.setName("Gabriel");
        user.setEmail("gabriel@example.com");
        user.setGender("Masculino");
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate birthDate = LocalDate.parse(input, f);
        user.setBirthDate(birthDate);
        user.setPassword("123");
        userMock = user;
    }

    @Test
    public void shouldInstanceObject() {
        Weight weight = new Weight();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        weight.setUser(userMock);
        weight.setMeasureDate(measureDate);
        weight.setMeasureValue(70.02);

        assertTrue(weight.getUser().equals(userMock));
        assertTrue(weight.getMeasureDate().equals(measureDate));
        assertTrue(weight.getMeasureValue() == 70.02);
    }
}
