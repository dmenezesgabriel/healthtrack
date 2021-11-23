package com.healttrack.entity;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.healthtrack.entity.User;
import com.healthtrack.entity.Workout;
import com.healthtrack.entity.WorkoutSession;

import org.junit.BeforeClass;
import org.junit.Test;

public class WorkoutSessionTest {
    public static User userMock = null;
    public static Workout workoutMock = null;

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

    public static void mockWorkout() {
        Workout workout = new Workout();
        workout.setName("Bike");
        assertTrue(workout.getName().equals("Bike"));
        workoutMock = workout;
    }

    @BeforeClass
    public static void setUp() {
        mockUser();
        mockWorkout();
    }

    @Test
    public void shouldInstanceObject() {
        WorkoutSession workoutSession = new WorkoutSession();
        String input = "1991-01-01";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate sessionDate = LocalDate.parse(input, f);
        workoutSession.setUser(userMock);
        workoutSession.setWorkout(workoutMock);
        workoutSession.setSessionDate(sessionDate);
        workoutSession.setDuration(3600); // Seconds
        workoutSession.setCalories(360);

    }
}
