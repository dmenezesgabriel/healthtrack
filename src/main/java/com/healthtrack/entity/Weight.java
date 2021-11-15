package com.healthtrack.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Weight implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int id;
    private User user;
    private LocalDate measureDate;
    private double measureValue;

    public Weight() {
    }

    public Weight(int id, User user, LocalDate measureDate, double measureValue) {
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

    public Weight id(int id) {
        setId(id);
        return this;
    }

    public Weight user(User user) {
        setUser(user);
        return this;
    }

    public Weight measureDate(LocalDate measureDate) {
        setMeasureDate(measureDate);
        return this;
    }

    public Weight measureValue(double measureValue) {
        setMeasureValue(measureValue);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Weight)) {
            return false;
        }
        Weight weight = (Weight) o;
        return id == weight.id && Objects.equals(user, weight.user) && Objects.equals(measureDate, weight.measureDate)
                && measureValue == weight.measureValue;
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
