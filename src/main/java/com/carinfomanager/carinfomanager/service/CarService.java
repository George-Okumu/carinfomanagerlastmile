package com.carinfomanager.carinfomanager.service;

import com.carinfomanager.carinfomanager.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAllCars();

    Optional<Car> getCarForSpecificUser(int user_id);
    Car saveCar(Car car);
}
