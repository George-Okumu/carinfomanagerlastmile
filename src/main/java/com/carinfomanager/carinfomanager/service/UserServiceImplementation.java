package com.carinfomanager.carinfomanager.service;

import com.carinfomanager.carinfomanager.models.User;
import com.carinfomanager.carinfomanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isAuthenticated(String username, String password, boolean isAdmin) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if (isAdmin) {
                return "admin".equals(username);
            } else {
                return !("admin".equals(username));
            }
        }

        return false;
    }


}
