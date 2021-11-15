package com.healthtrack.entity;

import java.io.Serializable;
import java.util.Objects;

public class Workout implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public Workout() {
    }

    public Workout(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workout id(int id) {
        setId(id);
        return this;
    }

    public Workout name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Workout)) {
            return false;
        }
        Workout workout = (Workout) o;
        return id == workout.id && Objects.equals(name, workout.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + "}";
    }

}
