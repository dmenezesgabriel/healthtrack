package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.healthtrack.entity.User;
import com.healthtrack.entity.Height;
import com.healthtrack.entity.Weight;
import com.healthtrack.entity.BodyMassIndex;

import org.junit.BeforeClass;
import org.junit.Test;

public class BodyMassIndexTest {
    public static User userMock = null;
    public static Height heightMock = null;
    public static Weight weightMock = null;

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

    public static void mockHeight() {
        Height height = new Height();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        height.setUser(userMock);
        height.setMeasureDate(measureDate);
        height.setMeasureValue(1.74);
        heightMock = height;
    }

    public static void mockWeight() {
        Weight weight = new Weight();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        weight.setUser(userMock);
        weight.setMeasureDate(measureDate);
        weight.setMeasureValue(70.02);
        weightMock = weight;
    }

    @BeforeClass
    public static void setUp() {
        mockUser();
        mockHeight();
        mockWeight();
    }

    @Test
    public void shouldInstanceObject() {
        BodyMassIndex bmi = new BodyMassIndex();
        bmi.setHeight(heightMock);
        bmi.setWeight(weightMock);
        bmi.setUser(userMock);
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate measureDate = LocalDate.parse(input, f);
        bmi.setMeasureDate(measureDate);
        bmi.setMeasureValue(bmi.calculateIndex());

        assertTrue(bmi.getUser().equals(userMock));
        assertTrue(bmi.getMeasureDate().equals(measureDate));
        assertTrue(bmi.getMeasureValue() == weightMock.getMeasureValue()
                / (heightMock.getMeasureValue() * weightMock.getMeasureValue()));
    }
}
