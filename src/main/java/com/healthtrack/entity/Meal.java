package com.healthtrack.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Meal implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int id;
    private User user;
    private Food food;
    private LocalDate mealDate;
    private int quantity;
    private double calories;

    public Meal() {
    }

    public Meal(int id, User user, Food food, LocalDate mealDate, int quantity, double calories) {
        this.id = id;
        this.user = user;
        this.food = food;
        this.mealDate = mealDate;
        this.quantity = quantity;
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

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public LocalDate getMealDate() {
        return this.mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCalories() {
        return this.calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Meal id(int id) {
        setId(id);
        return this;
    }

    public Meal user(User user) {
        setUser(user);
        return this;
    }

    public Meal food(Food food) {
        setFood(food);
        return this;
    }

    public Meal mealDate(LocalDate mealDate) {
        setMealDate(mealDate);
        return this;
    }

    public Meal quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Meal calories(double calories) {
        setCalories(calories);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Meal)) {
            return false;
        }
        Meal meal = (Meal) o;
        return id == meal.id && Objects.equals(user, meal.user) && Objects.equals(food, meal.food)
                && Objects.equals(mealDate, meal.mealDate) && quantity == meal.quantity && calories == meal.calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, food, mealDate, quantity, calories);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", user='" + getUser() + "'" + ", food='" + getFood() + "'"
                + ", mealDate='" + getMealDate() + "'" + ", quantity='" + getQuantity() + "'" + ", calories='"
                + getCalories() + "'" + "}";
    }

}
