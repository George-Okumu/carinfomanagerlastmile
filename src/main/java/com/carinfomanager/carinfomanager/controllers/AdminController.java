package com.carinfomanager.carinfomanager.controllers;

import com.carinfomanager.carinfomanager.models.Car;
import com.carinfomanager.carinfomanager.models.User;
import com.carinfomanager.carinfomanager.service.CarService;
import com.carinfomanager.carinfomanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private  CarService carService;


    public AdminController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }


    @GetMapping("")
    public String showAdminLogin(Model model) {
        return "adminlogin";
    }
    @GetMapping("/adminDashboard")
    public String showAdminDashboard(Model model) {
        model.addAttribute("listCars", carService.getAllCars());
        return "adminDashboard";
    }
    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @PostMapping("/login")
    public String processAdminLogin(@ModelAttribute User request, Model model) {

        // Authenticate the admin here (e.g., check credentials)
        if (userService.isAuthenticated(request.getUsername(), request.getPassword(), true)) {
            return "redirect:/admin/adminDashboard";
        } else {
            // Failed login attempt;
            model.addAttribute("error", "Access Denied, kindly contact super admin");
            return "adminlogin";
        }
    }
    @ModelAttribute("car")
    public Car getCar() {
        return new Car();
    }

    @PostMapping("/addVehicle")
    public String adminAddNewVehicle(@ModelAttribute Car request, Model model){
        if (request == null) {
            model.addAttribute("errorMessage", "Invalid data. Please try again.");
            return "addVehicleForm"; // Return the form with an error message
        }

        // Assuming you have a service to save the car to the database
        carService.saveCar(request);

        // You can also add a success message to the model for display in the view
        model.addAttribute("message", "Vehicle added successfully");

        return "redirect:/admin/adminDashboard";
    }

}
