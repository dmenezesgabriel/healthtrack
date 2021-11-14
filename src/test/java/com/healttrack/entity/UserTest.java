package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.healthtrack.entity.User;

import org.junit.Test;

public class UserTest {
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
        assertTrue(user.getEmail() == "gabriel@example.com");
        assertTrue(user.getGender() == "Masculino");
        assertTrue(user.getPassword() == "123");
        assertTrue(user.getBirthDate() == birthDate);

    }
}
