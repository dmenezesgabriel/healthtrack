package com.healthtrack.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BodyMassIndex implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int id;
    private User user;
    private Height height;
    private Weight weight;
    private LocalDate measureDate;
    private double measureValue;

    public BodyMassIndex() {
    }

    public BodyMassIndex(int id, User user, Height height, Weight weight, LocalDate measureDate, double measureValue) {
        this.id = id;
        this.user = user;
        this.height = height;
        this.weight = weight;
        this.measureDate = measureDate;
        this.measureValue = measureValue;
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

    public Height getHeight() {
        return this.height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Weight getWeight() {
        return this.weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public LocalDate getMeasureDate() {
        return this.measureDate;
    }

    public void setMeasureDate(LocalDate measureDate) {
        this.measureDate = measureDate;
    }

    public double getMeasureValue() {
        return this.measureValue;
    }

    public void setMeasureValue(double measureValue) {
        this.measureValue = measureValue;
    }

    public BodyMassIndex id(int id) {
        setId(id);
        return this;
    }

    public BodyMassIndex user(User user) {
        setUser(user);
        return this;
    }

    public BodyMassIndex height(Height height) {
        setHeight(height);
        return this;
    }

    public BodyMassIndex weight(Weight weight) {
        setWeight(weight);
        return this;
    }

    public BodyMassIndex measureDate(LocalDate measureDate) {
        setMeasureDate(measureDate);
        return this;
    }

    public BodyMassIndex measureValue(double measureValue) {
        setMeasureValue(measureValue);
        return this;
    }

    public double calculateIndex() {
        double bmi = this.weight.getMeasureValue() / (this.height.getMeasureValue() * this.weight.getMeasureValue());
        return bmi;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BodyMassIndex)) {
            return false;
        }
        BodyMassIndex bodyMassIndex = (BodyMassIndex) o;
        return id == bodyMassIndex.id && Objects.equals(user, bodyMassIndex.user)
                && Objects.equals(height, bodyMassIndex.height) && Objects.equals(weight, bodyMassIndex.weight)
                && Objects.equals(measureDate, bodyMassIndex.measureDate) && measureValue == bodyMassIndex.measureValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, height, weight, measureDate, measureValue);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", user='" + getUser() + "'" + ", height='" + getHeight() + "'"
                + ", weight='" + getWeight() + "'" + ", measureDate='" + getMeasureDate() + "'" + ", measureValue='"
                + getMeasureValue() + "'" + "}";
    }

}
