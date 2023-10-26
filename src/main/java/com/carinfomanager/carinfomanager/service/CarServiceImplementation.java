package com.carinfomanager.carinfomanager.service;

import com.carinfomanager.carinfomanager.models.Car;
import com.carinfomanager.carinfomanager.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImplementation implements  CarService{

    private CarRepository carRepository;

    public CarServiceImplementation(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarForSpecificUser(int user_id) {
        Optional<Car> caroptional = carRepository.findById(user_id);
        return caroptional;
    }

    @Override
    public Car saveCar(Car car) {
        if (car != null) {
            return carRepository.save(car);
        }
        return null;
    }
}
