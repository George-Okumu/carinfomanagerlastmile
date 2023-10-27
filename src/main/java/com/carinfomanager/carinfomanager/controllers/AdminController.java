package com.carinfomanager.carinfomanager.controllers;

import com.carinfomanager.carinfomanager.models.Car;
import com.carinfomanager.carinfomanager.models.User;
import com.carinfomanager.carinfomanager.repository.UserRepository;
import com.carinfomanager.carinfomanager.service.CarService;
import com.carinfomanager.carinfomanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private  CarService carService;

    private UserRepository userRepository;


    public AdminController(UserService userService, CarService carService, UserRepository userRepository) {
        this.userService = userService;
        this.carService = carService;
        this.userRepository = userRepository;
    }


    @GetMapping("")
    public String showAdminLogin(Model model) {
        return "admin/adminlogin";
    }
    @GetMapping("/adminDashboard")
    public String showAdminDashboard(Model model) {
        List<Car> cars = carService.getAllCars();

        if (cars.isEmpty()) {
            model.addAttribute("carRecordsNotFound", true);

        } else {
            model.addAttribute("listCars", cars);
        }
        return "admin/adminDashboard";
    }
    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @GetMapping("/new_vehicle")
    public String getForm(){
        return "admin/new_vehicle_admin";
    }

    @PostMapping("/login")
    public String processAdminLogin(@ModelAttribute User request, Model model) {

        // Authenticate the admin here (e.g., check credentials)
        if (userService.isAuthenticated(request.getUsername(), request.getPassword(), true)) {
            return "redirect:/admin/new_vehicle";
        } else {
            // Failed login attempt;
            model.addAttribute("error", "Access Denied, kindly contact super admin");
            return "admin/adminlogin";
        }
    }
    @ModelAttribute("car")
    public Car getCar() {
        return new Car();
    }

    @PostMapping("/addVehicle")
    public String adminAddNewVehicle(@ModelAttribute Car request, Model model){
        if (request.getMake() == null) {
            model.addAttribute("errorMessage", "Invalid data. Please try again.");
            return "my_record";
        }

        User user_car = userRepository.findByUsername("admin");
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

        return "redirect:/admin/adminDashboard";
    }

}
