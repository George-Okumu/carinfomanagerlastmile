package com.carinfomanager.carinfomanager.controllers;

import com.carinfomanager.carinfomanager.models.Car;
import com.carinfomanager.carinfomanager.models.User;
import com.carinfomanager.carinfomanager.service.CarService;
import com.carinfomanager.carinfomanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;
    private CarService carService;


    public UserController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
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
        Optional<Car> carOptional = carService.getCarForSpecificUser(6);
        if (carOptional.isPresent()) {
            Car userCar = carOptional.get();
            model.addAttribute("userCars", Collections.singletonList(userCar));
        } else {
            model.addAttribute("carRecordsNotFound", true);
        }
        return "user";
    }

    @PostMapping("/login")
    public String processUserLogin(@ModelAttribute User request, Model model) {
        // Authenticate the admin
        if (userService.isAuthenticated(request.getUsername(), request.getPassword(), false)) {
            return "redirect:/my_records";
        } else {
            // Failed login attempt;
            model.addAttribute("error", "Wrong username or password");
            return "userlogin";
        }
    }

    @ModelAttribute("user_car")
    public Car getUserCar() {
        return new Car();
    }
    @PostMapping("/new_vehicle")
    public String newVehicle(@ModelAttribute Car request, Model model){
        System.out.println(request.getMake());
        System.out.println(request.getMake());

        if (request == null) {
            model.addAttribute("errorMessage", "Invalid data. Please try again.");
            return "addVehicleForm"; // Return the form with an error message
        }

        // Assuming you have a service to save the car to the database
        carService.saveCar(request);

        // You can also add a success message to the model for display in the view
        model.addAttribute("message", "Vehicle added successfully");

        return "redirect:/my_records";
    }
}
