package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import com.healthtrack.entity.Workout;

import org.junit.Test;

public class WorkoutTest {

    @Test
    public void shoudInstanceObject() throws Exception {
        Workout workout = new Workout();
        workout.setName("Bike");
        assertTrue(workout.getName().equals("Bike"));
    }

}
