package com.carinfomanager.carinfomanager.controllers;

import com.carinfomanager.carinfomanager.models.Car;
import com.carinfomanager.carinfomanager.models.User;
import com.carinfomanager.carinfomanager.repository.UserRepository;
import com.carinfomanager.carinfomanager.service.CarService;
import com.carinfomanager.carinfomanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;
    private CarService carService;
    private UserRepository userRepository;



    public UserController(UserService userService, CarService carService, UserRepository userRepository) {
        this.userService = userService;
        this.carService = carService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public  String showUserLoginPage(){
        return "userlogin";
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @GetMapping("/my_records")
    public String getHomePage(Model model){
        User user = userRepository.findByUsername("data");
        List<Car> cars = user.getCars();
        if (cars.isEmpty()) {
            model.addAttribute("carRecordsNotFound", true);
        } else {
            System.out.println(cars.get(0).getUser().getRole());
            model.addAttribute("userCars", cars);
        }
        return "user";
    }

    @PostMapping("/login")
    public String processUserLogin(@ModelAttribute User request, Model model) {
        if (userService.isAuthenticated(request.getUsername(), request.getPassword(), false)) {
            return "redirect:/my_records";
        } else {
            // Failed login attempt;
            model.addAttribute("error", "Wrong username or password");
            return "userlogin";
        }
    }

    @ModelAttribute("user_car")
    public Car getCar() {
        return new Car();
    }
    @PostMapping("/new_vehicle")
    public String newVehicle(@ModelAttribute Car request, Model model){
        if (request.getMake() == null) {
            model.addAttribute("errorMessage", "Invalid data. Please try again.");
            return "my_record";
        }

        User user_car = userRepository.findByUsername("data");
        if (user_car == null) {
            // Handle the case where the user is not found, later
        }

        // Create and set the Car object
        Car car = new Car();
        car.setMake(request.getMake());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        // Set the "Entered By" property with the User object
        car.setUser(user_car);
        carService.saveCar(car);

        model.addAttribute("message", "Vehicle added successfully");

        return "redirect:/my_records";
    }
}
