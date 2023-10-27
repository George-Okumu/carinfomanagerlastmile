package com.carinfomanager.carinfomanager.controllers;

import com.carinfomanager.carinfomanager.models.Car;
import com.carinfomanager.carinfomanager.models.User;
import com.carinfomanager.carinfomanager.repository.CarRepository;
import com.carinfomanager.carinfomanager.service.CarService;
import com.carinfomanager.carinfomanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class CarController {
    private final CarService carService;
    private UserService userService;

    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<Car> getCars(Model model){
        List<Car> cars = carService.getAllCars();

        if(cars.size() > 1){
            return cars;
        }else{
            return null;
        }
    }
}
