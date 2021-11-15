package com.healthtrack.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Pressure implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int id;
    private User user;
    private LocalDate measureDate;
    private int systolicPressureValue;
    private int diastolicPressureValue;

    public Pressure() {
    }

    public Pressure(int id, User user, LocalDate measureDate, int systolicPressureValue, int diastolicPressureValue) {
        this.id = id;
        this.user = user;
        this.measureDate = measureDate;
        this.systolicPressureValue = systolicPressureValue;
        this.diastolicPressureValue = diastolicPressureValue;
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

    public int getSystolicPressureValue() {
        return this.systolicPressureValue;
    }

    public void setSystolicPressureValue(int systolicPressureValue) {
        this.systolicPressureValue = systolicPressureValue;
    }

    public int getDiastolicPressureValue() {
        return this.diastolicPressureValue;
    }

    public void setDiastolicPressureValue(int diastolicPressureValue) {
        this.diastolicPressureValue = diastolicPressureValue;
    }

    public Pressure id(int id) {
        setId(id);
        return this;
    }

    public Pressure user(User user) {
        setUser(user);
        return this;
    }

    public Pressure measureDate(LocalDate measureDate) {
        setMeasureDate(measureDate);
        return this;
    }

    public Pressure systolicPressureValue(int systolicPressureValue) {
        setSystolicPressureValue(systolicPressureValue);
        return this;
    }

    public Pressure diastolicPressureValue(int diastolicPressureValue) {
        setDiastolicPressureValue(diastolicPressureValue);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pressure)) {
            return false;
        }
        Pressure pressure = (Pressure) o;
        return id == pressure.id && Objects.equals(user, pressure.user)
                && Objects.equals(measureDate, pressure.measureDate)
                && systolicPressureValue == pressure.systolicPressureValue
                && diastolicPressureValue == pressure.diastolicPressureValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, measureDate, systolicPressureValue, diastolicPressureValue);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", user='" + getUser() + "'" + ", measureDate='" + getMeasureDate() + "'"
                + ", systolicPressureValue='" + getSystolicPressureValue() + "'" + ", diastolicPressureValue='"
                + getDiastolicPressureValue() + "'" + "}";
    }

}
