package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import com.healthtrack.entity.Workout;

import org.junit.Test;

public class WorkoutTest {

    @Test
    public void shoudInstanceObject() throws Exception {
        Workout Workout = new Workout();
        Workout.setName("Bike");
        assertTrue(Workout.getName().equals("Bike"));
    }

}
