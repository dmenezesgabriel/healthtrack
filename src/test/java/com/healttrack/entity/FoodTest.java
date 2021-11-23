package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import com.healthtrack.entity.Food;

import org.junit.Test;

public class FoodTest {

    @Test
    public void shoudInstanceObject() throws Exception {
        Food food = new Food();
        food.setName("Macarrão");
        assertTrue(food.getName().equals("Macarrão"));
    }

}
