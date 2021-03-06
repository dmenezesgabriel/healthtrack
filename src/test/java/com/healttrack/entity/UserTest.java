package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import com.healthtrack.entity.User;
import com.healthtrack.util.Cryptography;

import org.junit.Test;

public class UserTest {
    Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());

    @Test
    public void shoudInstanceObject() throws Exception {
        User user = new User();
        user.setName("Gabriel");
        user.setEmail("gabriel@example.com");
        user.setGender("Masculino");
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate birthDate = LocalDate.parse(input, f);
        user.setBirthDate(birthDate);
        user.setPassword(Cryptography.encrypt("123"));
        assertTrue(user.getName().equals("Gabriel"));
        assertTrue(user.getEmail().equals("gabriel@example.com"));
        assertTrue(user.getGender().equals("Masculino"));
        assertTrue(user.getPassword().equals(Cryptography.encrypt("123")));
        assertTrue(user.getBirthDate().equals(birthDate));
    }

}
