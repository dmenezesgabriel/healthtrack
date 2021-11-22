package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.healthtrack.entity.User;
import com.healthtrack.entity.Height;

import org.junit.Test;

public class HeightTest {

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
    public void shouldInstanceObject() {
        User user = mockUser();
        Height height = new Height();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        height.setUser(user);
        height.setMeasureDate(measureDate);
        height.setMeasureValue(1.74);

        assertTrue(height.getUser().equals(user));
        assertTrue(height.getMeasureDate().equals(measureDate));
        assertTrue(height.getMeasureValue() == 1.74);
    }
}
