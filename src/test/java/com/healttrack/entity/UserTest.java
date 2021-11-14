package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import com.healthtrack.entity.User;

import org.junit.Test;

public class UserTest {
    @Test
    public void shouldAnswerWithTrue() {
        User user = new User();
        user.setName("Gabriel");
        user.setEmail("gabriel@example.com");
        user.setGender("Masculino");
        user.setBirthDate(Calendar.getInstance());
        user.setPassword("123");
        assertTrue(user.getName() == "Gabriel");
        assertTrue(user.getEmail() == "gabriel@example.com");
        assertTrue(user.getGender() == "Masculino");
        assertTrue(user.getPassword() == "123");
    }
}
