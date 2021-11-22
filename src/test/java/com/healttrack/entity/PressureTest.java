package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.healthtrack.entity.User;
import com.healthtrack.entity.Pressure;

import org.junit.BeforeClass;
import org.junit.Test;

public class PressureTest {
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
        Pressure pressure = new Pressure();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        pressure.setUser(userMock);
        pressure.setMeasureDate(measureDate);
        int diastolicPressure = 90;
        int systolicPressure = 60;

        pressure.setDiastolicPressureValue(diastolicPressure);
        pressure.setSystolicPressureValue(systolicPressure);

        assertTrue(pressure.getUser().equals(userMock));
        assertTrue(pressure.getMeasureDate().equals(measureDate));
        assertTrue(pressure.getDiastolicPressureValue() == diastolicPressure);
        assertTrue(pressure.getSystolicPressureValue() == systolicPressure);

    }
}
