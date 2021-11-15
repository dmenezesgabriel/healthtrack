package com.healthtrack.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class WorkoutSession implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int id;
    private User user;
    private Workout workout;
    private LocalDate sessionDate;
    private long duration;
    private double calories;

    public WorkoutSession() {
    }

    public WorkoutSession(int id, User user, Workout workout, LocalDate sessionDate, long duration, double calories) {
        this.id = id;
        this.user = user;
        this.workout = workout;
        this.sessionDate = sessionDate;
        this.duration = duration;
        this.calories = calories;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workout getWorkout() {
        return this.workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public LocalDate getSessionDate() {
        return this.sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double getCalories() {
        return this.calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public WorkoutSession id(int id) {
        setId(id);
        return this;
    }

    public WorkoutSession user(User user) {
        setUser(user);
        return this;
    }

    public WorkoutSession workout(Workout workout) {
        setWorkout(workout);
        return this;
    }

    public WorkoutSession sessionDate(LocalDate sessionDate) {
        setSessionDate(sessionDate);
        return this;
    }

    public WorkoutSession duration(long duration) {
        setDuration(duration);
        return this;
    }

    public WorkoutSession calories(double calories) {
        setCalories(calories);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WorkoutSession)) {
            return false;
        }
        WorkoutSession workoutSession = (WorkoutSession) o;
        return id == workoutSession.id && Objects.equals(user, workoutSession.user)
                && Objects.equals(workout, workoutSession.workout)
                && Objects.equals(sessionDate, workoutSession.sessionDate) && duration == workoutSession.duration
                && calories == workoutSession.calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, workout, sessionDate, duration, calories);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", user='" + getUser() + "'" + ", workout='" + getWorkout() + "'"
                + ", sessionDate='" + getSessionDate() + "'" + ", duration='" + getDuration() + "'" + ", calories='"
                + getCalories() + "'" + "}";
    }

}
