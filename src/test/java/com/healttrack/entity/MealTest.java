package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.healthtrack.entity.User;
import com.healthtrack.entity.Food;
import com.healthtrack.entity.Meal;

import org.junit.BeforeClass;
import org.junit.Test;

public class MealTest {
    public static User userMock = null;
    public static Food FoodMock = null;

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

    public static void mockFood() {
        Food Food = new Food();
        Food.setName("Macarrão");
        assertTrue(Food.getName().equals("Macarrão"));
        FoodMock = Food;
    }

    @BeforeClass
    public static void setUp() {
        mockUser();
        mockFood();
    }

    @Test
    public void shouldInstanceObject() {
        Meal meal = new Meal();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate mealDate = LocalDate.parse(input, f);
        meal.setUser(userMock);
        meal.setFood(FoodMock);
        meal.setMealDate(mealDate);
        meal.setQuantity(1); // Seconds
        meal.setCalories(360);
        assertTrue(meal.getUser().equals(userMock));
        assertTrue(meal.getFood().equals(FoodMock));
        assertTrue(meal.getMealDate().equals(mealDate));
        assertTrue(meal.getQuantity() == 1);
        assertTrue(meal.getCalories() == 360);

    }
}
