package com.healthtrack.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.healthtrack.util.Cryptography;

public class User implements Serializable {
    protected static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String email;
    private String password;

    public User() {
    }

    public User(int id, String name, LocalDate birthDate, String gender, String email, String password) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        setPassword(password);
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

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        try {
            this.password = Cryptography.encrypt(password);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(birthDate, user.birthDate)
                && Objects.equals(gender, user.gender) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, gender, email, password);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", birthDate='" + getBirthDate() + "'"
                + ", gender='" + getGender() + "'" + ", email='" + getEmail() + "'" + ", password='" + getPassword()
                + "'" + "}";
    }

}
