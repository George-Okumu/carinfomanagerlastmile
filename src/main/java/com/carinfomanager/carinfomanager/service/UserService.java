package com.carinfomanager.carinfomanager.service;

import com.carinfomanager.carinfomanager.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    boolean isAuthenticated(String username, String password, boolean isAdmin);
}
