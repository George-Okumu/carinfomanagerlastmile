package com.carinfomanager.carinfomanager.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jdk.jfr.Label;
import org.springframework.context.annotation.Lazy;

import java.util.Objects;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Car {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String make;
    private String model;
    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Car(){};
    public Car(String make, String model, Integer year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public int getId() {
        return id;
    }
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return Objects.equals(make, car.make) && Objects.equals(model, car.model) && Objects.equals(year, car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year);
    }
}
