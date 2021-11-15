package com.healthtrack.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Height implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int id;
    private User user;
    private LocalDate measureDate;
    private double measureValue;

    public Height() {
    }

    public Height(int id, User user, LocalDate measureDate, double measureValue) {
        this.id = id;
        this.user = user;
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

    public Height id(int id) {
        setId(id);
        return this;
    }

    public Height user(User user) {
        setUser(user);
        return this;
    }

    public Height measureDate(LocalDate measureDate) {
        setMeasureDate(measureDate);
        return this;
    }

    public Height measureValue(double measureValue) {
        setMeasureValue(measureValue);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Height)) {
            return false;
        }
        Height height = (Height) o;
        return id == height.id && Objects.equals(user, height.user) && Objects.equals(measureDate, height.measureDate)
                && measureValue == height.measureValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, measureDate, measureValue);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", user='" + getUser() + "'" + ", measureDate='" + getMeasureDate() + "'"
                + ", measureValue='" + getMeasureValue() + "'" + "}";
    }
}
