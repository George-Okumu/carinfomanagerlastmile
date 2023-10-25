package com.carinfomanager.carinfomanager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public UserController() {
    }

    @GetMapping("/users")
    public String getUsers(){
        return "George";
    }
}
